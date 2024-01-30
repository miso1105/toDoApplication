package com.teamsparta.todo.domain.comment.dto

import jakarta.validation.constraints.NotBlank

data class RemoveCommentRequest(
    @field:NotBlank(message = "댓글 비밀번호를 입력해주세요")
    val password: String,
)
