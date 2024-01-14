package com.project.dday.service

import com.project.dday.model.Member
import com.project.dday.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun join(idfv: String) {
        if (!memberRepository.existsByIdfv(idfv = idfv)) {
            val newMember = Member(
                idfv = idfv,
            )
            memberRepository.save(newMember)
        }
    }
}
