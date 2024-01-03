package com.teamsparta.todo.domain.comment.dto


data class AddCommentRequest(
    val userName: String,
    val password: String,
    val content: String,
)
