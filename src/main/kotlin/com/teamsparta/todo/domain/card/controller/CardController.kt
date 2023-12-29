package com.teamsparta.todo.domain.card.controller


import com.teamsparta.todo.domain.card.dto.CardResponse
import com.teamsparta.todo.domain.card.dto.CreateCardRequest
import com.teamsparta.todo.domain.card.dto.UpdateCardRequest
import com.teamsparta.todo.domain.card.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/cards")
@RestController
class CardController(
    private val cardService: CardService            // 인터페이스 상속만으로 알아서 해당되는 빈을 찾아줌
) {

    @GetMapping
    fun getCardList(): ResponseEntity<List<CardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getAllCardList())     // controller와 service 연결
    }

    @GetMapping("/{cardId}")
    fun getCard(@PathVariable cardId: Long): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getCardById(cardId))
    }

    @PostMapping
    fun createCard(@RequestBody createCardRequest: CreateCardRequest): ResponseEntity<CardResponse> {
        // dto로 요청하는 바디가 있으니까 @RequestBody 어노테이션 사용. CreateCardRequest 감싸서 요청하고 status code 까지 반환되는 CardResponse로 감싼 엔티티를 받아야돼
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createCard(createCardRequest))
    }

    @PutMapping("/{cardId}")
    fun updateCard(
        @PathVariable cardId: Long,
        @RequestBody updateCardRequest: UpdateCardRequest
    ): ResponseEntity<CardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateCard(cardId, updateCardRequest))
    }

    @DeleteMapping("/{cardId}")
    fun deleteCard(@PathVariable cardId: Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}