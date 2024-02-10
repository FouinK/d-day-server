package com.project.dday.application.ask.port.`in`

import com.project.dday.model.Ask
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetMyAskListUseCase {
    fun getMyAskList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Ask>
}
