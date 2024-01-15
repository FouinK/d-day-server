package com.project.dday.service

import com.project.dday.model.Ask
import com.project.dday.model.Couple
import com.project.dday.repository.AskRepository
import com.project.dday.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class AskService(
    private val memberRepository: MemberRepository,
    private val askRepository: AskRepository,
) {
    fun getAskList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Ask> {
        val member = memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("") }
        return askRepository.findByMember(member, pageable)
    }

    @Transactional
    fun ask(
        memberId: Int,
        content: String,
    ) {
        val member = memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("") }

        val newData = Ask(
            content = content,
            member = member,
        )

        askRepository.save(newData)
    }

    fun validateAsk(askId: Int): Ask {
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
