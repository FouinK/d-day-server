package com.project.dday.application.member.action

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import com.project.dday.model.Member
import com.project.dday.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class PostJoinAction(
    private val memberRepository: MemberRepository,
) : PostJoinUseCase {
    override fun join(idfv: String) {
        if (!memberRepository.existsByIdfv(idfv = idfv)) {
            val newMember = Member(
                idfv = idfv,
            )
            memberRepository.save(newMember)
        }
    }
}
