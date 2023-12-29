package com.teamsparta.todo.domain.card.service

import com.teamsparta.todo.domain.card.dto.CardResponse
import com.teamsparta.todo.domain.card.dto.CreateCardRequest
import com.teamsparta.todo.domain.card.dto.UpdateCardRequest
import com.teamsparta.todo.domain.card.model.Card
import com.teamsparta.todo.domain.card.model.toResponse
import com.teamsparta.todo.domain.card.repository.CardRepository
import com.teamsparta.todo.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
): CardService {

    override fun getAllCardList(): List<CardResponse> {
        // TODO: DB에서 모든 카드 목록을 CardResponse로 감싼 리스트로 변환 후 반환
        return cardRepository.findAll().map { it.toResponse() }
    }


    override fun getCardById(cardId: Long): CardResponse {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID 기반으로 카드를 가져와서  CardResponse 감싸 변환 후 반환
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }



    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        // TODO: request를 Card라는 엔티티로 변환 후 저장
        if(request.userName == "" || request.title == "") {
            throw IllegalArgumentException("please enter your name and todo title")
        }

        return cardRepository.save(
            Card(
                userName = request.userName,
                title = request.title,
                plans = request.plans,
            )
        ).toResponse()
    }


    @Transactional
    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID에 해당하는 생성됐던 Card(Entitiy)를 가져와서 request(UpdateCardRequest)로 감싸 업데이트 한 후 DB에 저장, 결과를 CardResponse 로 감싸 변환 후 반환
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        val (title, plans) = request


        card.title = title
        card.plans = plans
        if (card.title == "") {
            throw IllegalArgumentException("please enter your todo title")
        }

        return cardRepository.save(card).toResponse()
    }


    @Transactional
    override fun deleteCard(cardId: Long) {
        // TODO: 만약 해당하는 카드 ID에 해당하는 카드가 없다면 throw ModelNotFoundExeption
        // TODO: DB에서 카드 ID에 해당하는 Card(Entitiy)를 삭제
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        return cardRepository.deleteById(cardId)

    }
}