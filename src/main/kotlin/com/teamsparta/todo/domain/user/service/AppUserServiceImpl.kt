package com.teamsparta.todo.domain.user.service

import com.teamsparta.todo.domain.exception.ModelNotFoundException
import com.teamsparta.todo.domain.user.dto.LoginRequest
import com.teamsparta.todo.domain.user.dto.LoginResponse
import com.teamsparta.todo.domain.user.dto.SignUpRequest
import com.teamsparta.todo.domain.user.dto.SignUpResponse
import com.teamsparta.todo.domain.user.exception.InvalidCredentialException
import com.teamsparta.todo.domain.user.model.AppUser
import com.teamsparta.todo.domain.user.model.Role
import com.teamsparta.todo.domain.user.model.toResponse
import com.teamsparta.todo.domain.user.repository.AppUserRepository
import com.teamsparta.todo.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AppUserServiceImpl(
    private val appUserRepository: AppUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {



    fun signUp(request: SignUpRequest): SignUpResponse {
        if (appUserRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use.")
        }

        return appUserRepository.save(
            AppUser (
                email = request.email,
                password = passwordEncoder.encode(request.password),
                role = when (request.role) {
                    "MEMBER" -> Role.MEMBER
                    "ADMIN" -> Role.ADMIN
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }



    fun login(request: LoginRequest): LoginResponse {
        val appUser = appUserRepository.findByEmail(request.email) ?: throw ModelNotFoundException("AppUser", null)

        if (appUser.role.name != request.role || !passwordEncoder.matches(request.password, appUser.password)) {
            throw InvalidCredentialException()
        }


        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = appUser.id.toString(),
                email = appUser.email,
                role = appUser.role.name
            )
        )

    }
}