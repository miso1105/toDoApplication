package com.teamsparta.todo.domain.todo.service
import com.teamsparta.todo.domain.comment.dto.CommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.toResponse
import com.teamsparta.todo.domain.comment.repository.CommentRepository
import com.teamsparta.todo.domain.exception.ModelNotFoundException
import com.teamsparta.todo.domain.exception.UnathorizedException
import com.teamsparta.todo.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todo.domain.todo.dto.TodoResponse
import com.teamsparta.todo.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todo.domain.todo.model.DoneStatus
import com.teamsparta.todo.domain.todo.model.Todo
import com.teamsparta.todo.domain.todo.model.toResponse
import com.teamsparta.todo.domain.todo.repository.TodoRepository
import com.teamsparta.todo.domain.user.repository.AppUserRepository
import com.teamsparta.todo.infra.security.AuthenticationUtil.getAuthenticationId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,          // 컨트롤러에서 결합된 서비스에 스프링이 왔는데 서비스와 레포지토리가 연결돼있음. 스피링이 레포지토리 호출.
    private val commentRepository: CommentRepository,
    private val appUserRepository: AppUserRepository,
) : TodoService {
    override fun getPaginatedTodoList(pageable: Pageable, status: String?): Page<TodoResponse>? {
                                                                                        // 이렇게 스트링화 된 이넘클래스의 상수값들을 다시 이넘클래스로 비교하는 이유는 QueryDsl 에서 status 를 비교할 때 이때는 String 기반으로 비교하는 게 아니라 원래의 자료타입이었던 Enum 으로 비교해서 Enum 으로 다시 조건문을 바꿔줘야 한다.
        val doneStatus = when(status) {
            "TRUE" -> DoneStatus.TRUE
            "FALSE" -> DoneStatus.FALSE
            null -> null                                                               // null 값이 올 수도 있다는 가정을 항상 하는 거 같아. 오류 날까봐
            else -> throw IllegalArgumentException("The Done Status is invalid")       // TRUE, FALSE 외의 값은 예외처리
        }

        return todoRepository.findByPageableAndStatus(pageable, doneStatus)?.map { it.toResponse() }    // TodoResponse dto 싸서 던져야돼. 컨트롤러에서 페이징한 투두리스폰스 리스트로 반환받는다고 했어
    }                                                                                               // Page<U> map, 실제 가져온 contents 에 대해서 map 을 한다. U 자리에 Todo 가 들어가니까.


//    override fun getTodoList(sortedByDate: String): List<TodoResponse> {
//        return when (sortedByDate.lowercase()) {
//            "asc" -> {
//                todoRepository.findAll().map { it.toResponse() }.sortedBy { it.createdDate }
//            }
//            "desc" -> {
//                todoRepository.findAll().map { it.toResponse() }.sortedByDescending { it.createdDate }
//            } else -> throw IllegalArgumentException("Please select a sort option.")
//        }
//    }

    override fun getTodoById(todoId: Long): TodoResponse {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID 기반으로 할일을 가져와서  CardResponse 감싸 변환 후 반환
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }


    // Querydsl 사용
    // commentRepositoryImpl 이 리펙토링을 통해 CommentRepository 를 바라보고 있으니까 return commentRepositoryImpl.searchCommentListBycommentUserName(commentUserName).map { it.toResponse() } 에서 생성자 주입 지우고 리펙토링.
    override fun searchCommentList(commentId: Long): List<CommentResponse> {
        return commentRepository.searchCommentListBycommentId(commentId).map { it.toResponse() }
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        val authenticatedId = getAuthenticationId()
        appUserRepository.findByIdOrNull(authenticatedId) ?: throw UnathorizedException("권한이 없습니다")

        return todoRepository.save(
            Todo(
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

        todo.update(request)

//        val (title, plans) = request
//
//        todo.title = title
//        todo.plans = plans

//        // 수정할 때 askTOdoStatusIsDoneOrNot 요청칸에 done을 쓰면 할일 상태가 TRUE로 바뀜
//        if (request.askTOdoStatusIsDoneOrNot.lowercase() == "done")
//            todo.updateDoneStatus()
//
//        // 수정할 때 askTOdoStatusIsDoneOrNot 요청칸에 not을 쓰면 할일 상태가 TRUE 에서 FALSE로 바뀜
//        if (request.askTOdoStatusIsDoneOrNot.lowercase() == "not")
//            todo.updateNotStatus()

        return todoRepository.save(todo).toResponse()
    }


    override fun deleteTodo(todoId: Long) {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 할일 ID에 해당하는 Todo(Entitiy)를 삭제
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        todoRepository.delete(todo)
    }


    @Transactional
    override fun addComment(todoId: Long, request: CommentRequest): CommentResponse {
        // TODO: 만약 해당하는 할일 ID에 해당하는 할일이 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 할일 ID에 해당하는 Todo(Entitiy) 내 댓글 작성
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        val comment = Comment(
            content = request.content,
            todo = todo
        )
        todo.addComment(comment)
        todoRepository.save(todo)

        return commentRepository.save(comment).toResponse()
    }


    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: CommentRequest): CommentResponse {
        // TODO: 댓글 작성자의 이름과 비밀번호를 알아야 댓글 수정 가능, 일치하지 않는다면 throw IllegalStateException
        // TODO: DB에서 할일 ID와 댓글ID에 해당하는 Todo(Entitiy) 내 댓글 수정
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Comment", commentId)
        val comment: Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        comment.update(request)

        todoRepository.save(todo)
        return commentRepository.save(comment).toResponse()
    }




    override fun removeComment(todoId: Long, commentId: Long, request: CommentRequest) {
        // TODO: 댓글 작성자의 이름과 비밀번호를 알아야 댓글 삭제 가능, 일치하지 않는다면 throw IllegalStateException
        // TODO: DB에서 할일 ID와 댓글ID에 해당하는 Todo(Entitiy) 내 댓글 삭제
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment: Comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)


        todo.removeComment(comment)
        todoRepository.save(todo)
    }
}
