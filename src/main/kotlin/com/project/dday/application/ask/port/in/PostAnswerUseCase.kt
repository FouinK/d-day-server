package com.project.dday.application.ask.port.`in`

interface PostAnswerUseCase {
    fun answer(
        memberId: Int,
        content: String,
        askId: Int,
    )
}
