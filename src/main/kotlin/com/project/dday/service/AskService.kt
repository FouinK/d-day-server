package com.project.dday.service

import com.project.dday.model.Ask
import com.project.dday.model.Couple
import com.project.dday.repository.AskRepository
import org.springframework.stereotype.Service

@Service
class AskService(
    private val askRepository: AskRepository,
) {
    fun findExistsAsk(askId: Int): Ask {
        return askRepository.findById(askId)
            .orElseThrow { IllegalArgumentException("질문이 없습니다.") }
    }

    fun validateAskForMe(
        ask: Ask,
        couple: Couple,
    ): Boolean {
        return ask.member.id != couple.member1 && ask.member.id != couple.member2
    }
}
