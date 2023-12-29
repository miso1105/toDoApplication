package com.teamsparta.todo.domain.card.service

import com.teamsparta.todo.domain.card.dto.CardResponse
import com.teamsparta.todo.domain.card.dto.CreateCardRequest
import com.teamsparta.todo.domain.card.dto.UpdateCardRequest

interface CardService {

    fun getAllCardList(): List<CardResponse>

    fun getCardById(cardId: Long): CardResponse

    fun createCard(request: CreateCardRequest): CardResponse

    fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse

    fun deleteCard(cardId: Long)

}