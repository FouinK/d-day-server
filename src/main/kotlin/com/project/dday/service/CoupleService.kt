package com.project.dday.service

import com.project.dday.model.Couple
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class CoupleService(
    private val coupleRepository: CoupleRepository,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun connect(
        memberId: Int,
        idfv: String,
    ) {
        val member1 = memberRepository.findById(memberId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 회원의 요청입니다.") }

        val member2 = memberRepository.findByIdfv(idfv)
            ?: throw IllegalArgumentException("커플 맺을 사람이 존재하지 않습니다.")

        if (coupleRepository.existsByMember1OrMember2(member1.id!!, member2.id!!) ||
            coupleRepository.existsByMember1OrMember2(member2.id, member1.id)
        ) {
            throw IllegalArgumentException("이미 존재하는 커플 관계 입니다.")
        }

        val newData = Couple(
            member1 = member1.id,
            member2 = member2.id,
        )

        coupleRepository.save(newData)
    }
}
