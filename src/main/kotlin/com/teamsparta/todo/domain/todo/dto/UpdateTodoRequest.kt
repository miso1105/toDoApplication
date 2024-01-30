package com.teamsparta.todo.domain.todo.dto

import jakarta.validation.constraints.NotBlank

data class UpdateTodoRequest(
    @field:NotBlank(message = "제목을 입력해주세요")
    val title: String,
    val plans: String?,
    @field:NotBlank(message = "Done: 할 일 완료/ Not: 할 일 진행 중")
    var askTOdoStatusIsDoneOrNot: String,
)
