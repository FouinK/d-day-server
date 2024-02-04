package com.project.dday.application.answer.port.`in`

import com.project.dday.model.Answer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetAnswerListUseCase {
    fun getAnswerList(
        memberId: Int,
        pageable: Pageable,
    ): Page<Answer>
}
