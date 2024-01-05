package com.teamsparta.todo.domain.todo.dto


import com.teamsparta.todo.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val plans: String?,
    val createdDate: LocalDateTime,
    var doneStatus: String,
    var commentList: List<CommentResponse>,
)
