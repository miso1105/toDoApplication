package com.teamsparta.todo.domain.todo.dto


import com.fasterxml.jackson.annotation.JsonFormat
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val userName: String,
    val title: String,
    val plans: String?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdDate: LocalDateTime,
    var doneStatus: String,
    var commentList: List<CommentResponse>,
)
