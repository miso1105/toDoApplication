package com.teamsparta.todo.domain.card.model

import com.teamsparta.todo.domain.card.dto.CardResponse
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime



@Entity
@Table(name = "card")
class Card(

    @Column(name = "user_name")   // Supabase 컬럼 명과 같아야 함
    var userName: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "plans")
    var plans: String? = null,

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


}

fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        userName = userName,
        title = title,
        plans = plans,
        createdDate = LocalDateTime.now(),
    )
}
