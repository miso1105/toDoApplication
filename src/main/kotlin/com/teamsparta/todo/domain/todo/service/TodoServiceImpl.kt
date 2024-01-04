package com.teamsparta.todo.domain.todo.service

import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.RemoveCommentRequest
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.toResponse
import com.teamsparta.todo.domain.comment.repository.CommentRepository
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.model.Todo
import com.teamsparta.todo.domain.todo.model.toResponse
import com.teamsparta.todo.domain.todo.repository.TodoRepository
import com.teamsparta.todo.domain.exception.ModelNotFoundException
import com.teamsparta.todo.domain.todo.model.DoneStatus
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,          // 컨트롤러에서 결합된 서비스에 스프링이 왔는데 서비스와 레포지토리가 연결돼있음. 스피링이 레포지토리 호출.
    private val commentRepository: CommentRepository
) : TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        // TODO: DB에서 모든 할일 목록을 CardResponse로 감싼 리스트로 변환 후 반환
        return todoRepository.findAll().map { it.toResponse() }
    }


    override fun getTodoById(todoId: Long): TodoResponse {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID 기반으로 할일을 가져와서  CardResponse 감싸 변환 후 반환
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }


    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        // TODO: request를 Todo라는 엔티티로 변환 후 저장
        if (request.userName == "" || request.title == "") {
            throw IllegalArgumentException("please enter your name and todo title")
        }

        return todoRepository.save(
            Todo(
                userName = request.userName,
                title = request.title,
                plans = request.plans,
                doneStatus = DoneStatus.FALSE,
            )
        ).toResponse()
    }


    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 할일 ID에 해당하는 생성됐던 Todo(Entitiy)를 가져와서 request(UpdateCardRequest)로 감싸 업데이트 한 후 DB에 저장, 결과를 CardResponse 로 감싸 변환 후 반환
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        val (title, plans) = request

        todo.title = title
        todo.plans = plans
        if (request.title == "") {
            throw IllegalArgumentException("please enter your todo title")
        }

        // 수정할 때 askTOdoStatusIsDoneOrNot 요청칸에 done을 쓰면 할일 상태가 TRUE로 바뀜
        if (request.askTOdoStatusIsDoneOrNot.lowercase() == "done")
            todo.updateDoneStatus()

        return todoRepository.save(todo).toResponse()
    }


    @Transactional
    override fun deleteTodo(todoId: Long) {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 할일 ID에 해당하는 Todo(Entitiy)를 삭제
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        todoRepository.delete(todo)
    }


    @Transactional
    override fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 할일 ID에 해당하는 Todo(Entitiy) 내 댓글 작성
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        val comment = Comment(
            commentUserName = request.commentUserName,
            password = request.password,
            content = request.content,
            todo = todo
        )
        todo.addComment(comment)
        todoRepository.save(todo)
        return comment.toResponse()
//        return commentRepository.save(comment).toResponse()
    }


    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        // TODO: 댓글 작성자의 이름과 비밀번호를 알아야 댓글 수정 가능, 일치하지 않는다면 throw IllegalStateException
        // TODO: DB에서 할일 ID와 댓글ID에 해당하는 Todo(Entitiy) 내 댓글 수정
        val comment: Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        val (_, _, content) = request

        if (request.commentUserName != comment.commentUserName || request.password != comment.password) {
            throw IllegalStateException("Your name and password are incorrect. Please try again.")
        }

            comment.content = content
            return commentRepository.save(comment).toResponse()
    }



    @Transactional
    override fun removeComment(todoId: Long, commentId: Long, request: RemoveCommentRequest) {
        // TODO: 댓글 작성자의 이름과 비밀번호를 알아야 댓글 삭제 가능, 일치하지 않는다면 throw IllegalStateException
        // TODO: DB에서 할일 ID와 댓글ID에 해당하는 Todo(Entitiy) 내 댓글 삭제
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment: Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        val (_, _) = request

        if (request.commentUserName != comment.commentUserName || request.password != comment.password) {
            throw IllegalStateException("Your name and password are incorrect. Please try again.")
        }

        todo.removeComment(comment)
        todoRepository.save(todo)
    }
}