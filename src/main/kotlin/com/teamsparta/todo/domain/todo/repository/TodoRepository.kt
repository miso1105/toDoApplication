package com.teamsparta.todo.domain.todo.repository

import com.teamsparta.todo.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository


interface TodoRepository: JpaRepository<Todo, Long>    // 컨트롤러 - 서비스 - 레포지토리가 결합돼 자동으로 인스턴스 하나가 생성돼 기능들이 실행 가능한 것