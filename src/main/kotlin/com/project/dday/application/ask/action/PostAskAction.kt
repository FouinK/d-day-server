package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.application.ask.port.`in`.PostAskUseCase.*
import com.project.dday.model.Ask
import com.project.dday.repository.AskRepository
import com.project.dday.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PostAskAction(
    private val memberService: MemberService,
    private val askRepository: AskRepository,
) : PostAskUseCase {
    @Transactional
    override fun ask(
        memberId: Int,
        content: String,
    ): PostAskResponseDto {
        val member = memberService.validateLoginMember(memberId)

        val newData = Ask(
            content = content,
            member = member,
        )

        val ask = askRepository.save(newData)

        return PostAskResponseDto(
            askId = ask.id,
        )
    }
}
