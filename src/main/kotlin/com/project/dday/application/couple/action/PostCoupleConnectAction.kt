package com.project.dday.application.couple.action

import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.CoupleConnectException
import com.project.dday.model.Couple
import com.project.dday.repository.CoupleRepository
import com.project.dday.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PostCoupleConnectAction(
    private val memberService: MemberService,
    private val coupleRepository: CoupleRepository,
) : PostCoupleConnectUseCase {
    @Transactional
    override fun connect(
        memberId: Int,
        idfv: String,
    ) {
        val member1 = memberService.validateLoginMember(memberId)

        if (member1.idfv == idfv) {
            throw CoupleConnectException("자기 자신과 커플을 맺을 수 없습니다.")
        }

        val member2 = memberService.validateIdfvMember(idfv)

        if (coupleRepository.existsByMember1Id(member1.id)) {
            throw CoupleConnectException("당신은 이미 커플이 맺어진 상태입니다.")
        }

        coupleRepository.saveAll(
            listOf(
                Couple(
                    member1Id = member1.id,
                    member2Id = member2.id,
                ),
                Couple(
                    member1Id = member2.id,
                    member2Id = member1.id,
                ),
            ),
        )
    }
}
