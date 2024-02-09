package com.project.dday.action.answer.action

import com.project.dday.application.answer.action.PostAnswerAction
import com.project.dday.application.answer.port.`in`.PostAnswerUseCase
import com.project.dday.application.ask.action.PostAskAction
import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.application.couple.action.PostCoupleConnectAction
import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.model.AnswerStatus
import com.project.dday.repository.AnswerRepository
import com.project.dday.repository.AskRepository
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.AskService
import com.project.dday.service.CoupleService
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
class PostAnswerActionTest(
    @Autowired val memberService: MemberService,
    @Autowired val askService: AskService,
    @Autowired val askRepository: AskRepository,
    @Autowired val coupleService: CoupleService,
    @Autowired val answerRepository: AnswerRepository,
    @Autowired val memberRepository: MemberRepository,
    @Autowired val coupleRepository: CoupleRepository,
) {
    lateinit var postAnswerUseCase: PostAnswerUseCase
    lateinit var postAskUseCase: PostAskUseCase
    lateinit var postCoupleConnectUseCase: PostCoupleConnectUseCase

    var memberId1: Int = 0
    var memberId2: Int = 0

    val memberIdfv1 = "윤성현"
    val memberIdfv2 = "로이"
    val askContent = "질문 내용"
    val answerContent = "응답 내용"

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

        postCoupleConnectUseCase = PostCoupleConnectAction(
            memberService,
            coupleRepository,
        )

        postAnswerUseCase = PostAnswerAction(
            memberService,
            askService,
            askRepository,
            coupleService,
            answerRepository,
        )

        postCoupleConnectUseCase.connect(
            memberId1,
            member2.idfv,
        )
    }

    @Test
    fun `나를 위한 질문에 정상 응답한다`() {
        // given
        val askResponseDto = postAskUseCase.ask(
            memberId = memberId1,
            content = askContent,
        )

        // when
        val answerResponseDto = postAnswerUseCase.answer(
            memberId = memberId2,
            content = answerContent,
            askId = askResponseDto.askId,
        )

        val answer = answerRepository.findById(answerResponseDto.answerId)
            .orElseThrow { throw NotFoundException("답변이 정상 저장되지 않았습니다.") }

        val ask = askRepository.findById(askResponseDto.askId)
            .orElseThrow { throw NotFoundException("질문이 정상 저장되지 않았습니다.") }

        // then
        assertThat(answer.content).isEqualTo(answerContent)
        assertThat(ask.status).isEqualTo(AnswerStatus.COMPLETE)
    }
}
