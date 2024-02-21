package com.project.dday.application.couple.action

import com.project.dday.application.couple.port.`in`.DeleteCoupleDeConnectUseCase
import com.project.dday.exception.NoCoupleException
import com.project.dday.exception.NotFoundException
import com.project.dday.repository.CoupleRepository
import org.springframework.stereotype.Component

@Component
class DeleteCoupleDeConnectAction(
    private val coupleRepository: CoupleRepository,
) : DeleteCoupleDeConnectUseCase {
    override fun deConnect(memberId: Int) {
        val member1Couple = coupleRepository.findByMember1Id(memberId)
            ?: throw NotFoundException("당신은 커플 상태가 아닙니다.")

        val member2Couple = coupleRepository.findByMember1Id(member1Couple.member2Id)
            ?: throw NotFoundException("당신은 커플 상태가 아닙니다.")

        if (member1Couple.deConnect || member2Couple.deConnect) {
            throw NoCoupleException("이미 커플관계가 끊어진 상태입니다.")
        }

        member1Couple.deConnect()
        member2Couple.deConnect()

        coupleRepository.saveAll(
            listOf(
                member1Couple,
                member2Couple,
            )
        )
    }
}
