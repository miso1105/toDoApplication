package com.teamsparta.todo.domain.comment.dto

data class UpdateCommentRequest(
    val commentUserName: String,
    val password: String,
    val content: String,
    )
