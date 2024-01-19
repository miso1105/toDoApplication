package com.teamsparta.todo.domain.user.dto

data class SignUpResponse(
    val id: Long,
    val email: String,
    val password: String,
    val role: String,
)
