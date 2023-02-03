package com.project.dday.application.ask.action

import com.project.dday.model.Ask
import com.project.dday.repository.AskRepository
import com.project.dday.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PostAskAction(
    private val memberService: MemberService,
    private val askRepository: AskRepository,
) {
    @Transactional
    fun ask(
        memberId: Int,
        content: String,
    ) {
        val member = memberService.validateLoginMember(memberId)

        val newData = Ask(
            content = content,
            member = member,
        )

        askRepository.save(newData)
    }
}
