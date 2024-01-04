package com.teamsparta.todo.domain.todo.controller


import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService            // 인터페이스 상속만으로 알아서 해당되는 빈을 찾아줌 , Service와 연결돼 서비스 방향을 바라보고 있음 = 스프링이 서비스 호출
) {


    // 할일 목록 조회 - 할일 생성 날짜 기준 오름차순 리스트로 받을건지 내림차순 리스트로 받을건지 요청
    @GetMapping("/{order}")
    fun getAscOrDescTodoList(@PathVariable order: String) {
    }

    // order == asc -> asc url 접속 후 할일 목록을 할일 생성 날짜 기준 오름차순 리스트로 반환
    @GetMapping("/asc")
    fun getAscTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAscTodoList())
    }

    // order == desc -> desc url 접속 후 할일 목록을 할일 생성 날짜 기준 내림차순 리스트로 반환
    @GetMapping("/desc")
    fun getDescTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getDescTodoList())           // controller와 service 연결
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    @PostMapping
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        // dto로 요청하는 바디가 있으니까 @RequestBody 어노테이션 사용. CreateCardRequest 감싸서 요청하고 status code 까지 반환되는 CardResponse로 감싼 엔티티를 받아야돼
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}