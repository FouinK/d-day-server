package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.model.AnswerStatus
import com.project.dday.repository.AskRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostAskActionTest(
    @Autowired val memberService: MemberService,
    @Autowired val askRepository: AskRepository,
    @Autowired val memberRepository: MemberRepository,
) {
    lateinit var postAskUseCase: PostAskUseCase
    var memberId1: Int = 0
    var memberId2: Int = 0

    val memberIdfv1 = "윤성현"
    val memberIdfv2 = "로이"
    val content = "질문 내용"

    @BeforeEach
    fun setUp() {
        val member1 = memberRepository.save(
            MemberBuilder(
                idfv = memberIdfv1,
            ).build(),
        )

        val member2 = memberRepository.save(
            MemberBuilder(
                idfv = memberIdfv2,
            ).build(),
        )

        memberId1 = member1.id
        memberId2 = member2.id

        postAskUseCase = PostAskAction(
            memberService,
            askRepository,
        )
    }

    @Test
    fun `내가 질문한 목록이 정상 저장 되었음을 확인한다`() {
        // when
        val askResponseDto = postAskUseCase.ask(
            memberId = memberId1,
            content = content,
        )

        // when
        val ask = askRepository.findById(askResponseDto.askId)
            .orElseThrow { throw NotFoundException("질문이 정상저장 되지 않았습니다.") }

        // then
        assertThat(ask.content).isEqualTo(content)
        assertThat(ask.status).isEqualTo(AnswerStatus.READY)
    }
}
