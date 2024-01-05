package com.teamsparta.todo.domain.comment.dto

data class RemoveCommentRequest(
    val commentUserName: String,
    val password: String,
)
