package com.teamsparta.todo.domain.comment.repository

import com.teamsparta.todo.domain.comment.model.Comment


interface CustomCommentRepository {

    fun searchCommentListBycommentUserName(commentUserName: String): List<Comment>
}