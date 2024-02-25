package com.project.dday.application.member.port.`in`

interface PostJoinUseCase {
    fun join(idfv: String): PostJoinResponseDto

    class PostJoinResponseDto(
        val memberId: Int,
        val sessionId: String,
    )
}
