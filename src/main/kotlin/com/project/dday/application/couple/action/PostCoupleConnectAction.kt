package com.project.dday.application.couple.action

import com.project.dday.model.Couple
import com.project.dday.repository.CoupleRepository
import com.project.dday.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Component
class PostCoupleConnectAction(
    private val memberService: MemberService,
    private val coupleRepository: CoupleRepository,
) {
    @Transactional
    fun connect(
        memberId: Int,
        idfv: String,
    ) {
        val member1 = memberService.validateLoginMember(memberId)

        val member2 = memberService.validateIdfvMember(idfv)

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
