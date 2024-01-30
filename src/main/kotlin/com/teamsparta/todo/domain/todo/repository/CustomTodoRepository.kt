package com.teamsparta.todo.domain.todo.repository

import com.teamsparta.todo.domain.todo.model.DoneStatus
import com.teamsparta.todo.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomTodoRepository {

    fun findByPageableAndStatus(pageable: Pageable, doneStatus: DoneStatus?): Page<Todo>?
}