package com.teamsparta.todo.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val commentUserName: String,
    val content: String,
)
