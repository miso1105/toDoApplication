package com.teamsparta.todo.domain.user.repository

import com.teamsparta.todo.domain.user.model.AppUser
import org.springframework.data.jpa.repository.JpaRepository

interface AppUserRepository: JpaRepository<AppUser, Long> {
    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): AppUser?
}