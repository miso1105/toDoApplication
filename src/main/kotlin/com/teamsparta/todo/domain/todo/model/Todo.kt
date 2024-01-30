package com.teamsparta.todo.domain.todo.model

import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.toResponse
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.infra.security.AuthenticationUtil.getAuthenticationNickName
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "todo")
class Todo(

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


    fun update(request: UpdateTodoRequest) {
        // 수정할 때 askTOdoStatusIsDoneOrNot 요청칸에 done을 쓰면 할일 상태가 TRUE로 바뀜
        if (request.askTOdoStatusIsDoneOrNot.lowercase() == "done") updateDoneStatus()
        // 수정할 때 askTOdoStatusIsDoneOrNot 요청칸에 not을 쓰면 할일 상태가 TRUE 에서 FALSE로 바뀜
        if (request.askTOdoStatusIsDoneOrNot.lowercase() == "not") updateNotStatus()

        title = request.title
        plans = request.plans
        return
    }
}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        nickName = getAuthenticationNickName(),
        title = title,
        plans = plans,
        createdDate = createdDate,
        doneStatus = doneStatus.name,
        commentList = comments.map { it.toResponse() },
    )
}

