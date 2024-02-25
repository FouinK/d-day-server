package com.project.dday.application.member.action

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import com.project.dday.model.Member
import com.project.dday.model.MemberSession
import com.project.dday.repository.MemberRepository
import com.project.dday.repository.MemberSessionRepository
import com.project.dday.service.CustomAuthenticationManagerService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class PostJoinAction(
    private val memberRepository: MemberRepository,
    private val memberSessionRepository: MemberSessionRepository,
    private val customAuthenticationManagerService: CustomAuthenticationManagerService,
) : PostJoinUseCase {
    override fun join(idfv: String): PostJoinUseCase.PostJoinResponseDto {
        if (!memberRepository.existsByIdfv(idfv = idfv)) {
            val newMember =
                Member(
                    idfv = idfv,
                )
            memberRepository.save(newMember)
        }

        val authentication =
            customAuthenticationManagerService.authenticate(
                UsernamePasswordAuthenticationToken(idfv, idfv),
            )

        SecurityContextHolder.getContext().authentication = authentication

        val mallMemberSession = memberSessionRepository.findByMemberId(authentication.credentials as Int)

        if (mallMemberSession != null) {
            memberSessionRepository.deleteById(mallMemberSession.sessionId)
        }

        val newData =
            MemberSession(
                sessionId = UUID.randomUUID().toString(),
                memberId = authentication.credentials as Int,
            )

        memberSessionRepository.save(newData)

        return PostJoinUseCase.PostJoinResponseDto(
            sessionId = newData.sessionId,
        )
    }
}
