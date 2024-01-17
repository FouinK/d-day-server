package com.project.dday.service

import com.project.dday.model.Couple
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CoupleService(
    private val coupleRepository: CoupleRepository,
    private val memberRepository: MemberRepository,
) {

    fun validateCouple(memberId: Int): Couple {
        return coupleRepository.findByMember1OrMember2(
            member1 = memberId,
            member2 = memberId,
        ) ?: throw IllegalArgumentException("나는 커플 상태가 아닙니다.")
    }
}
