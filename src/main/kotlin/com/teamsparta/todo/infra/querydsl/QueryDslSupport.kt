package com.teamsparta.todo.infra.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

// QueryDslSupport 직접 사용을 안해서 추상 클래스로 만듦
abstract class QueryDslSupport {

    // EntityManager 필요할 때 @PersistenceContext 이용
    @PersistenceContext
    protected lateinit var entityManager: EntityManager


    // 다른 곳에서도 쓸 수 있게 쿼리 팩토리를 만들어야 한다.
    protected val queryFactory: JPAQueryFactory
        get() {
            return JPAQueryFactory(entityManager)
        }
}