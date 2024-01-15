package com.project.dday.service

import com.project.dday.model.Ask
import com.project.dday.repository.AskRepository
import com.project.dday.repository.MemberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
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
}
