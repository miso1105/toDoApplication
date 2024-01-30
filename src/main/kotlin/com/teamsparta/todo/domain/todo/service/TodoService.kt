package com.teamsparta.todo.domain.todo.service
import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.RemoveCommentRequest
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TodoService {

//    fun getTodoList(sortedByDate: String): List<TodoResponse>
    fun getPaginatedTodoList(pageable: Pageable, status: String?): Page<TodoResponse>?

    fun getTodoById(todoId: Long): TodoResponse

    fun createTodo(request: CreateTodoRequest): TodoResponse

    fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteTodo(todoId: Long)

    fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun removeComment(todoId: Long, commentId: Long, request: RemoveCommentRequest)

    fun searchCommentList(commentUserName: String): List<CommentResponse>
}