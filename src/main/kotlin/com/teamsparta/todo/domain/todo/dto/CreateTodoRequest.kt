package com.teamsparta.todo.domain.todo.dto

import com.teamsparta.todo.domain.todo.model.DoneStatus

data class CreateTodoRequest(
    val userName: String,
    val title: String,
    val plans: String?,

)
