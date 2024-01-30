package com.teamsparta.todo.domain.todo.controller


import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.service.TodoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService            // 인터페이스 상속만으로 알아서 해당되는 빈을 찾아줌 , Service와 연결돼 서비스 방향을 바라보고 있음 = 스프링이 서비스 호출
) {
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun getTodoList(
        @PageableDefault(size = 10, sort = ["id"]) pageable: Pageable,
        @RequestParam(value = "doneStatus", required = false) status: String?     // doneStatus 로 필터링 해야 된다 -> 동적 쿼리 작성의 기준이 됨, 근데 없을수도 있어서 required = false 로 널값 허용하고,
//        @RequestParam("sortedByDate") sortedByDate: String
    ): ResponseEntity<Page<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getPaginatedTodoList(pageable, status))
//            .body(todoService.getTodoList(sortedByDate))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PostMapping
    fun createTodo(@Valid @RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @Valid @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}