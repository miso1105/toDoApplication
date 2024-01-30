package com.teamsparta.todo.domain.todo.repository

import com.teamsparta.todo.domain.comment.repository.CustomCommentRepository
import com.teamsparta.todo.domain.todo.model.DoneStatus
import com.teamsparta.todo.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface TodoRepository: JpaRepository<Todo, Long>, CustomTodoRepository {

}