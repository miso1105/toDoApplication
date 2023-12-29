package com.teamsparta.todo.domain.exception.dto

// 메시지를 반환하고 싶어서 감싸서 변환 후 반환하기 위해 만든 데이터 클래스
data class ErrorResponse(
    val message: String?
)
