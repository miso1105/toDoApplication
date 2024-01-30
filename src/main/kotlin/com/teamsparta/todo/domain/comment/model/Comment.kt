package com.teamsparta.todo.domain.comment.model

import com.teamsparta.todo.domain.comment.dto.CommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null



    fun update(request: CommentRequest) {
        content = request.content
    }

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        content = content,
    )
}
