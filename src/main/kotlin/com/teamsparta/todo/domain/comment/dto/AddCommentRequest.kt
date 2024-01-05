package com.teamsparta.todo.domain.comment.dto


data class AddCommentRequest(
    val commentUserName: String,
    val password: String,
    val content: String,
)
