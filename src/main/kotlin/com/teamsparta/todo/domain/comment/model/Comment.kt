package com.teamsparta.todo.domain.comment.model

import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "comment_user_name")
    val commentUserName: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        commentUserName = commentUserName,
        content = content
    )
}