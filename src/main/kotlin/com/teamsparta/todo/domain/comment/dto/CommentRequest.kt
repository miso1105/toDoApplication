package com.teamsparta.todo.domain.comment.dto

import jakarta.validation.constraints.NotBlank


data class CommentRequest(
    @field:NotBlank(message = "댓글 내용을 입력해주세요")
    val content: String,
)
