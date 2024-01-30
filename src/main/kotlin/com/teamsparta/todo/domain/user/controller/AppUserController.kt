package com.teamsparta.todo.domain.user.controller

import com.teamsparta.todo.domain.user.dto.LoginRequest
import com.teamsparta.todo.domain.user.dto.LoginResponse
import com.teamsparta.todo.domain.user.dto.SignUpRequest
import com.teamsparta.todo.domain.user.dto.SignUpResponse
import com.teamsparta.todo.domain.user.service.AppUserServiceImpl
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AppUserController(
    private val appUserService: AppUserServiceImpl
) {

    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(appUserService.signUp(signUpRequest))
    }


    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(appUserService.login(loginRequest))
    }
}