package com.teamsparta.todo.domain.card.dto

data class UpdateCardRequest(
    val title: String,
    val plans: String?,
)
