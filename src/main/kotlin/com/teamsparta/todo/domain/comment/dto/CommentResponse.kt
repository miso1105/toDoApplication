package com.teamsparta.todo.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val email: String,
    val content: String,
)
