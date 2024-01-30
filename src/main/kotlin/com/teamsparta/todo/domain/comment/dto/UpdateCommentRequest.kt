package com.teamsparta.todo.domain.comment.dto

import jakarta.validation.constraints.NotBlank

data class UpdateCommentRequest(
    @field:NotBlank(message = "댓글 비밀번호를 입력해주세요")
    val password: String,
    @field:NotBlank(message = "댓글 내용을 입력해주세요")
    val content: String,
    )
