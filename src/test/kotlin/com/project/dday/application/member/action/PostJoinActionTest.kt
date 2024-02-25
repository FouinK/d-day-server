package com.project.dday.application.member.action

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.repository.MemberRepository
import com.project.dday.repository.MemberSessionRepository
import com.project.dday.service.CustomAuthenticationManagerService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostJoinActionTest(
    @Autowired val memberRepository: MemberRepository,
    @Autowired val memberSessionRepository: MemberSessionRepository,
    @Autowired val customAuthenticationManagerService: CustomAuthenticationManagerService,
) {
    lateinit var postJoinUseCase: PostJoinUseCase
    val idfv = "윤성현"

    @BeforeEach
    fun setUp() {
        postJoinUseCase =
            PostJoinAction(
                memberRepository,
                memberSessionRepository,
                customAuthenticationManagerService,
            )
    }

    @Test
    fun `최초 접속시 정상적으로 sessionId를 응답한다`() {
        // when
        val postJoinResponseDto = postJoinUseCase.join(idfv)

        val member =
            memberRepository.findById(postJoinResponseDto.memberId)
                .orElseThrow { throw NotFoundException("멤버가 존재하지 않습니다. 테스트 실패") }

        val memberSession =
            memberSessionRepository.findByMemberId(member.id)
                ?: throw NotFoundException("세션이 존재하지 않습니다. 테스트 실퍄")

        assertThat(member.idfv).isEqualTo(member.idfv)
        assertThat(memberSession.memberId).isEqualTo(member.id)
    }
}
