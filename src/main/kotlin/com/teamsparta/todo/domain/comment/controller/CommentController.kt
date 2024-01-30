package com.teamsparta.todo.domain.comment.controller

import com.teamsparta.todo.domain.comment.dto.CommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comments")
@RestController
class CommentController(
    private val todoService: TodoService
) {

/*    // 쿼리디에셀 사용 api
    @GetMapping
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    fun searchCommentList(
        @RequestParam(value = "commentUserName") commentUserName: String
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.searchCommentList(commentUserName))
    }*/

    // id 정렬 다시 만들기

    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PostMapping
    fun addComment(
        @PathVariable todoId: Long,
        @Valid @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.addComment(todoId, commentRequest))
    }


    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long, @PathVariable commentId: Long,
        @Valid @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateComment(todoId, commentId, commentRequest))
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{commentId}")
    fun removeComment(
        @PathVariable todoId: Long, @PathVariable commentId: Long,
        @Valid @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(todoService.removeComment(todoId, commentId, commentRequest))
    }
}