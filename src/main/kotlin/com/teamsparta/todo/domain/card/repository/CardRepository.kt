package com.teamsparta.todo.domain.card.repository

import com.teamsparta.todo.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository


interface CardRepository: JpaRepository<Card, Long>