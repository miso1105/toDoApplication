package com.teamsparta.todo.domain.todo.service

import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.model.Todo
import com.teamsparta.todo.domain.todo.model.toResponse
import com.teamsparta.todo.domain.todo.repository.TodoRepository
import com.teamsparta.todo.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
): TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        // TODO: DB에서 모든 카드 목록을 CardResponse로 감싼 리스트로 변환 후 반환
        return todoRepository.findAll().map { it.toResponse() }
    }


    override fun getTodoById(todoId: Long): TodoResponse {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID 기반으로 카드를 가져와서  CardResponse 감싸 변환 후 반환
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }


    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        // TODO: request를 Card라는 엔티티로 변환 후 저장
        if(request.userName == "" || request.title == "") {
            throw IllegalArgumentException("please enter your name and todo title")
        }

        return todoRepository.save(
            Todo(
                userName = request.userName,
                title = request.title,
                plans = request.plans,
            )
        ).toResponse()
    }


    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID에 해당하는 생성됐던 Card(Entitiy)를 가져와서 request(UpdateCardRequest)로 감싸 업데이트 한 후 DB에 저장, 결과를 CardResponse 로 감싸 변환 후 반환
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        val (title, plans) = request


        todo.title = title
        todo.plans = plans
        if (todo.title == "") {
            throw IllegalArgumentException("please enter your todo title")
        }

        return todoRepository.save(todo).toResponse()
    }


    @Transactional
    override fun deleteTodo(todoId: Long) {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID에 해당하는 Card(Entitiy)를 삭제
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        return todoRepository.delete(todo)

    }
}