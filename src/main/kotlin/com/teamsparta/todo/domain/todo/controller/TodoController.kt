package com.teamsparta.todo.domain.todo.controller


import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService            // 인터페이스 상속만으로 알아서 해당되는 빈을 찾아줌 , Service와 연결돼 서비스 방향을 바라보고 있음 = 스프링이 서비스 호출
) {



    @GetMapping
//    @Secured    // 옛날에 나오는 어노테이션이라 잘 안씀
    @PreAuthorize("hasRole('ADMIN')")
    // SpringSecurityExpressionRoot 에서 사용하는 hasPermission, hasRole 메서드들을 자유롭게 이 어노테이션 안에서 사용 가능, Authentication 객체에도 접근이 가능하고, 어노테이션 내 argument 에도 접근이 가능, 그래서 @Secured는 왠만해서 안하고 @PreAuthorize 를 사용,
    // 여기서 권한 인증이 실패되면 AccessDeniedException 오류가 뜬다.
    fun getTodoList(
        @RequestParam("sortedByDate") sortedByDate: String
    ): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList(sortedByDate))
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
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        // dto로 요청하는 바디가 있으니까 @RequestBody 어노테이션 사용. CreateCardRequest 감싸서 요청하고 status code 까지 반환되는 CardResponse로 감싼 엔티티를 받아야돼
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }

    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
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