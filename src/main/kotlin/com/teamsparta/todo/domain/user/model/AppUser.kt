package com.teamsparta.todo.domain.user.model

import com.teamsparta.todo.domain.user.dto.SignUpResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class AppUser (

    @Column(name = "email")
    val email: String,

    @Column(name = "nickname")
    val nickName: String,

    @Column(name = "password")
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: Role,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun AppUser.toResponse(): SignUpResponse {
    return SignUpResponse(
        id = id!!,
        email = email,
        nickName = nickName,
        password = password,
        role = role.name

    )
}