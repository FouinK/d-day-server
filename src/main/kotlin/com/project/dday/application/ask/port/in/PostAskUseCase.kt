package com.project.dday.application.ask.port.`in`

interface PostAskUseCase {
    fun ask(
        memberId: Int,
        content: String,
    )
}
