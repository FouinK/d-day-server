package com.project.dday.application.ask.port.`in`

import com.project.dday.dto.AskListForMeResponseDto
import org.springframework.data.domain.Pageable

interface GetAskListForMeUseCase {
    fun getAskListForMe(
        memberId: Int,
        pageable: Pageable,
    ): AskListForMeResponseDto
}
