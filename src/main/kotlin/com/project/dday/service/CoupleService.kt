package com.project.dday.service

import com.project.dday.model.Couple
import com.project.dday.repository.CoupleRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CoupleService(
    private val coupleRepository: CoupleRepository,
) {
    fun validateCouple(memberId: Int): Couple {
        return coupleRepository.findByMember1Id(
            member1Id = memberId,
        ) ?: throw IllegalArgumentException("나는 커플 상태가 아닙니다.")
    }
}
