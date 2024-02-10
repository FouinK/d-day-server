package com.project.dday.service

import com.project.dday.exception.NotFoundException
import com.project.dday.model.Member
import com.project.dday.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun validateLoginMember(memberId: Int): Member {
        return memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("") }
    }

    fun validateIdfvMember(idfv: String): Member {
        return memberRepository.findByIdfv(idfv)
            ?: throw java.lang.IllegalArgumentException("커플 맺을 사람이 존재하지 않습니다.")
    }

    fun validateMember(memberId: Int): Member {
        return memberRepository.findById(memberId)
            .orElseThrow { throw NotFoundException("멤버가 존재하지 않습니다.") }
    }
}
