package com.teamsparta.todo.domain.todo.dto

import jakarta.validation.constraints.NotBlank

data class CreateTodoRequest(
    @field:NotBlank(message = "제목을 입력해주세요")
    val title: String,
    val plans: String?,
)
