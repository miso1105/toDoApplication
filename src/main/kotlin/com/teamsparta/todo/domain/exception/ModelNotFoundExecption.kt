package com.teamsparta.todo.domain.exception



data class ModelNotFoundException(val modelName: String, val id: Long): RuntimeException(         // RuntimeException => applicaton이 실행될 떄 발생되는 예외
    "Model $modelName not found with given id: $id"                // Model card not found with given id: 1
)