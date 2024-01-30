package com.teamsparta.todo.domain.todo.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import com.teamsparta.todo.domain.comment.model.QComment
import com.teamsparta.todo.domain.todo.model.DoneStatus
import com.teamsparta.todo.domain.todo.model.QTodo
import com.teamsparta.todo.domain.todo.model.Todo
import com.teamsparta.todo.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl: QueryDslSupport(), CustomTodoRepository {

    private val todo = QTodo.todo

    override fun findByPageableAndStatus(pageable: Pageable, doneStatus: DoneStatus?): Page<Todo>? {
        val whereClause = BooleanBuilder()       // true , false 반환해주는 불리언 빌더
        // ?.let 널이 아니라면은 { } 안에 있는거 해, and 조건을 넣어줘서 todo.doneStatus 가 우리가 받아온 doneStatus 와 동일하다면 => where 조건절
        doneStatus?.let { whereClause.and(todo.doneStatus.eq(doneStatus)) }



        // 우리가 최종적으로 PageImpl()을 리턴할건데 content: <Mutable>List<Todo!> , pageable: Pageable, total: Long 을 인자로 넣어줘야 해
        // 먼저  total: Long 토탈을 하려면 Count query 를 써야겠지
        // queryFactory 로 쿼리 사용
        val totalCount = queryFactory.select(todo.count()).from(todo).where(whereClause).fetchOne() ?: 0L  // 단건일때 fetchFirst() 는 여러개 조회하고 limit(1) 맨위 하나만 추출,
        // TodoServiceImpl 에서 DoneStatus 널값도 허용했으니 TRUE, FALSE가 없는 널값은 0개로 표시 ( ?: 0L )

        // pageable: Pageable 인자 쿼리
        // queryFactory 로 쿼리 사용
//        val contents = queryFactory.selectFrom(todo)

        val comment = QComment.comment      // comment EntityPathBase 정의
        val query = queryFactory.selectFrom(todo)
            .where(whereClause)
            .leftJoin(todo.comments, comment)         // left join == todo 테이블을 기준으로 comment 를 연결해준다는 뜻 , 두 테이블이 join 됨
            .fetchJoin()                              // 레프트 조인 이후 fetchJoin() 을 설정하면 됨
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
//            .orderBy(todo.id.asc())        // id 별로 오름차순 정렬
//            .orderBy(todo.title.asc())     // title 별로 오름차순 정렬
        // 위 orderBy 가 여러조건일 수도 있으니 동적 쿼리로도 써봄
/*            .orderBy(*getOrderSpecifier(pageable, todo))
            .fetch()*/

//        // 만약 pageable 에 컨트롤러 내 sort = ["id"] 와 같이 sort 가 @PageableDefault 에 있다면,
//        if (pageable.sort.isSorted) {
//            // order 가 하나만 있다고 가정하며 first() 를  사용한다면,
//            if (pageable.sort.first()?.property == "id") {
//                query.orderBy(todo.id.asc())       // 위에서 만든 query 를 기점으로 나중에 orderBy 를 나중에 동적으로 붙일 수 있다.
//            } else if (pageable.sort.first()?.property == "title") {
//                query.orderBy(todo.title.asc())
//            } else {
//                query.orderBy(todo.id.asc())
//            }
//        } else {
//            query.orderBy(todo.id.asc())
//        }

        // 위 contents 인자에 들어갈 query when() 절로 리펙토링
        // orderBy 가 동적으로 걸리게 된다
        if (pageable.sort.isSorted) {
            when (pageable.sort.first()?.property) {
                "id" -> query.orderBy(todo.id.asc())
                "title" -> query.orderBy(todo.title.asc())
                else -> query.orderBy(todo.id.asc())
            }
        } else {
            query.orderBy(todo.id.asc())
        }

        val contents = query.fetch()  // fetch() :리스트로 결과를 반환하는 방법. (만약에 데이터가 없으면 빈 리스트를 반환)

        return PageImpl(contents, pageable, totalCount)  // 알아서 엘리먼트가 몇개고 페이지 사이즈가 몇인지 알아서 반환해준다.
    }

////    // orderBy 에 조건이 여러개 올 수 있다고 가정한다면 동적으로 편하게 OrderSpecifier 를 쓸 수 있다
//    private fun getOrderSpecifier(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {   // Pageable 에서 order 목록을 추출해서 OrderSpecifier 목록을 만들 수 있다.
//        val pathBuilder = PathBuilder(path.type, path.metadata)
//
//        return pageable.sort.toList().map {
//            order -> OrderSpecifier(
//                if (order.isAscending) Order.ASC else  Order.DESC,
//                pathBuilder.get(order.property) as Expression<Comparable<*>>
//            )
//        }.toTypedArray()
//    }
}