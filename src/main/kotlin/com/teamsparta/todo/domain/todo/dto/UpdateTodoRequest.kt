package com.teamsparta.todo.domain.todo.dto

data class UpdateTodoRequest(
    val title: String,
    val plans: String?,
    var askTOdoStatusIsDoneOrNot: String,
)
