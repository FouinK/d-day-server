package com.project.dday.application.answer.port.`in`

interface PostAnswerUseCase {
    fun answer(
        memberId: Int,
        content: String,
        askId: Int,
    ): PostAnswerResponseDto

    class PostAnswerResponseDto(
        val answerId: Int,
    )
}
