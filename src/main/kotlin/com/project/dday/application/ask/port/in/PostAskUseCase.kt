package com.project.dday.application.ask.port.`in`

interface PostAskUseCase {
    fun ask(
        memberId: Int,
        content: String,
    ): PostAskResponseDto

    class PostAskResponseDto(
        val askId: Int,
    )
}
