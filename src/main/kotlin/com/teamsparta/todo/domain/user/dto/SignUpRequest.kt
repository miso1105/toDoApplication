package com.teamsparta.todo.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val role: String,
)
