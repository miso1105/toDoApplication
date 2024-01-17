package com.teamsparta.todo.domain.todo.model

<<<<<<< HEAD
import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.toResponse
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import jakarta.persistence.*
import java.time.LocalDateTime



@Entity
@Table(name = "todo")
class Todo(

    @Column(name = "user_name")   // Supabase 컬럼 명과 같아야 함
    var userName: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "plans")
    var plans: String? = null,

    @Column(name = "date_time")
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "done_status")
    var doneStatus: DoneStatus,

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }

    // 할일 완료하면 호출되는 메소드
    fun updateDoneStatus() {
        doneStatus = DoneStatus.TRUE
    }

    // 할일 완료를 취소하면 호출되는 메소드
    fun updateNotStatus() {
        doneStatus = DoneStatus.FALSE
    }

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        userName = userName,
        title = title,
        plans = plans,
        createdDate = createdDate,
        doneStatus = doneStatus.name,
        commentList = comments.map { it.toResponse() },
    )
}
