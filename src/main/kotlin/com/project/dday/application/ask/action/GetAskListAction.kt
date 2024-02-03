package com.project.dday.application.ask.action

import com.project.dday.model.Ask
import com.project.dday.repository.AskRepository
import com.project.dday.service.MemberService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetAskListAction(
    private val memberService: MemberService,
    private val askRepository: AskRepository,
) {
    fun getAskList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Ask> {
        val member = memberService.validateLoginMember(memberId)
        return askRepository.findByMember(member, pageable)
    }
}
