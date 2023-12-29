package com.teamsparta.todo.domain.card.dto

import java.time.LocalDateTime

data class CardResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val plans: String?,
    val createdDate: LocalDateTime,
)
