package com.teamsparta.todo.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val userName: String,
    val content: String,
)
