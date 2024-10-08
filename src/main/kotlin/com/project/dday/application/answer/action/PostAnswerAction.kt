@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.project.dday.application.answer.action

import com.project.dday.application.answer.port.`in`.PostAnswerUseCase
import com.project.dday.application.answer.port.`in`.PostAnswerUseCase.*
import com.project.dday.exception.AnswerException
import com.project.dday.model.Answer
import com.project.dday.repository.AnswerRepository
import com.project.dday.repository.AskRepository
import com.project.dday.service.AskService
import com.project.dday.service.CoupleService
import com.project.dday.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PostAnswerAction(
    private val memberService: MemberService,
    private val askService: AskService,
    private val askRepository: AskRepository,
    private val coupleService: CoupleService,
    private val answerRepository: AnswerRepository,
) : PostAnswerUseCase {
    @Transactional
    override fun answer(
        memberId: Int,
        content: String,
        askId: Int,
    ): PostAnswerResponseDto {
        val member = memberService.validateLoginMember(memberId = memberId)

        val ask = askService.findExistsAsk(askId = askId)

        val couple = coupleService.validateCouple(memberId = memberId)

        if (askService.validateAskForMe(ask, couple)) {
            throw AnswerException("나를 위한 질문이 아닙니다.")
        }

        if (answerRepository.existsByAskId(ask.id)) {
            throw AnswerException("이미 답변했습니다.")
        }

        ask.answer()

        askRepository.save(ask)

        val newData =
            Answer(
                content = content,
                askId = ask.id,
                member = member,
            )

        val answer = answerRepository.save(newData)

        return PostAnswerResponseDto(
            answerId = answer.id,
        )
    }
}
