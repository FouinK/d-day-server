package com.project.dday.service

import com.project.dday.model.Answer
import com.project.dday.repository.AnswerRepository
import com.project.dday.repository.AskRepository
import com.project.dday.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.IllegalArgumentException

@Service
class AnswerService(
    private val memberRepository: MemberRepository,
    private val answerRepository: AnswerRepository,
    private val askRepository: AskRepository,
) {
    fun getAnswerList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Answer> {
        val member = memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("") }
        return answerRepository.findByMember(member, pageable)
    }

    fun answer(
        memberId: Int,
        content: String,
        askId: Int,
    ) {
        val member = memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("") }

        val ask = askRepository.findById(askId)
            .orElseThrow { IllegalArgumentException("질문이 없습니다.") }

        if (answerRepository.existsByAskId(ask.id!!)) {
            throw IllegalArgumentException("이미 답변했습니다.")
        }

        val newData = Answer(
            content = content,
            askId = ask.id!!,
            member = member,
        )

        answerRepository.save(newData)
    }
}
