package com.teamsparta.todo.infra.security
import com.teamsparta.todo.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDenialHandler: AccessDeniedHandler
) {



    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }   // basic authentication 은 인증할 때 우리가 토큰 기반으로 인증하는 것과는 다르게 Base64 인코드를 해서 비밀번호를 보내는 형태(filter 제외), 이거 끄면 DefaultLoginPage도 같이 꺼버림
            .formLogin { it.disable() }   // 이걸 끄면 DefaultLogin, Logout 필터를 끄고 UsernamePasswordAuthenticationFilter 필터 다 꺼버림
            .csrf { it.disable() }       // 지금은 서버 없이 stateless한 스웨거만 사용하기 때문에 방화벽 세울 필요 없음
            .authorizeHttpRequests{
                // 근데 이제 밑에서 다 막으면 스웨거도 못켜 403 에러 뜸, 그래서 부분 허용도 같이 넣어줘야 돼
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
//                    .requestMatchers("/member/**").hasRole("MEMBER") // 멤버인 유저만 하위단에서 다 기능 쓸 수 있어. ROLE_ 는 Authority(인가체킹 객체) 내 permission 과 구분짓는 prefix 이다
                    .anyRequest().authenticated()
            }     // 인증 돼야지 할일, 댓글 쓸 수 있다는 것을 시큐리티에게 알려줘야 한다. 어떤 Request 던 얘는 인증이 돼야 돼.
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)  // 필터를 어떤 기준으로 어떤 필터를 넣어줄 것인지 씀. 원래 UsernamePasswordAuthenticationFilter 가 있던 자리에 직접 만든 jwtAuthenticationFilter 가 UsernamePasswordAuthenticationFilter 필터 앞단에 들어감.
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)   // 커스텀한 엔트리 포인트
                it.accessDeniedHandler(accessDenialHandler)
            }
            .build()
    }
}