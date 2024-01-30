package com.teamsparta.todo.infra.security


import org.springframework.security.core.context.SecurityContextHolder

object AuthenticationUtil {
    private fun getAuthenticatedUser(): UserPrincipal =
        SecurityContextHolder.getContext().authentication.principal as UserPrincipal

    fun getAuthenticationId() = getAuthenticatedUser().id

    fun getAuthenticationNickName() = getAuthenticatedUser().nickName
}
