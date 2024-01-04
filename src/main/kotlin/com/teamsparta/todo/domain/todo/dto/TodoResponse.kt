package com.teamsparta.todo.domain.todo.dto

import com.teamsparta.todo.domain.todo.model.DoneStatus
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val plans: String?,
    val createdDate: LocalDateTime,
    var doneStatus: String,
)
