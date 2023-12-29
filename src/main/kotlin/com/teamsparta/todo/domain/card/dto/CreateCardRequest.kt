package com.teamsparta.todo.domain.card.dto

data class CreateCardRequest(
    val userName: String,
    val title: String,
    val plans: String?,

)
