package com.teamsparta.todo.domain.todo.dto

data class CreateTodoRequest(
    val userName: String,
    val title: String,
    val plans: String?,

)
