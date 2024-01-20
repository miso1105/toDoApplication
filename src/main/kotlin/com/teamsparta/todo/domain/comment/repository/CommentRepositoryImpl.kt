package com.teamsparta.todo.domain.comment.repository

import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.QComment
import com.teamsparta.todo.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

// 원래 사용하던 CommentRepository에는 JpaRepository 를 상속받아서 리포지토리 어노테이션을 안 썼던 것.
@Repository
class CommentRepositoryImpl: QueryDslSupport(), CustomCommentRepository {   // CommentRepositoryImpl 이 인터페이스 CustomCommentRepository 를 구현하는 구현체

    private val comment = QComment.comment

    override fun searchCommentListBycommentUserName(commentUserName: String): List<Comment> {
        return queryFactory.selectFrom(comment)
            .where(comment.commentUserName.containsIgnoreCase(commentUserName))      // containsIgnoreCase: 대소문자 구분없이 쓸 수 있다.
            .fetch()
    }


}