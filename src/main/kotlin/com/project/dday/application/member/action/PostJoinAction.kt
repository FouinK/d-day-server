package com.project.dday.application.member.action

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import com.project.dday.exception.NotFoundException
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
        val newMember =
            if (!memberRepository.existsByIdfv(idfv = idfv)) {
                val newMember =
                    Member(
                        idfv = idfv,
                    )
                memberRepository.save(newMember)
            } else {
                memberRepository.findByIdfv(idfv)
                    ?: throw NotFoundException("멤버가 존재하지 않습니다. 시스템 오류입니다.")
            }

        val authentication =
            customAuthenticationManagerService.authenticate(
                UsernamePasswordAuthenticationToken(newMember.id, newMember.id),
            )

        SecurityContextHolder.getContext().authentication = authentication

        val memberSession = memberSessionRepository.findByMemberId(authentication.credentials.toString().toInt())

        if (memberSession != null) {
            memberSessionRepository.deleteById(memberSession.id)
        }

        val newData =
            MemberSession(
                id = UUID.randomUUID().toString(),
                memberId = authentication.credentials.toString().toInt(),
            )

        memberSessionRepository.save(newData)

        return PostJoinUseCase.PostJoinResponseDto(
            memberId = newData.memberId,
            sessionId = newData.id,
        )
    }
}
