package com.teamsparta.todo.domain.exception

class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
