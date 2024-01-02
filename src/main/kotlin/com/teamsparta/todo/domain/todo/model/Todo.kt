package com.teamsparta.todo.domain.todo.model

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

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        userName = userName,
        title = title,
        plans = plans,
        createdDate = LocalDateTime.now(),
    )
}
