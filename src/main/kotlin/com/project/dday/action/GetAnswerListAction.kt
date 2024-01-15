package com.project.dday.action

import com.project.dday.model.Answer
import com.project.dday.repository.AnswerRepository
import com.project.dday.service.MemberService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetAnswerListAction(
    private val memberService: MemberService,
    private val answerRepository: AnswerRepository,
) {
    fun getAnswerList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Answer> {
        val member = memberService.validateLoginMember(memberId = memberId)
        // TODO : querydsl 추가해야함
        return answerRepository.findByMember(member, pageable)
    }
}
