package com.teamsparta.todo.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.secret}") private val secret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {


    // 검증 parsing
    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key =
                Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(
        subject: String,
        email: String,
        nickName: String,
        role: String
    ): String {
        return generateToken(subject, email, nickName, role, Duration.ofHours(accessTokenExpirationHour))

    }

    private fun generateToken(subject: String, email: String, nickName: String, role: String, expirationPeriod: Duration): String {
        val claims: Claims = Jwts.claims()
            .add(mapOf("role" to role, "email" to email, "nickName" to nickName))
            .build()

        val now = Instant.now()
        val expirationPeriod = Duration.ofHours(168)
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))


        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))  // 토큰 발행 시간, Instant는 인간이 읽을 수 없는 시간의 정수표기법
            .expiration(Date.from(now.plus(expirationPeriod)))    // 토큰 만료 시간
            .claims(claims)
            .signWith(key)
            .compact()
    }


}