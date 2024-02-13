@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.GetAskListForMeUseCase
import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.application.couple.action.PostCoupleConnectAction
import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.NoCoupleException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.repository.AskRepository
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class GetAskListForMeActionTest(
    @Autowired val memberService: MemberService,
    @Autowired val coupleRepository: CoupleRepository,
    @Autowired val askRepository: AskRepository,
    @Autowired val memberRepository: MemberRepository,
) {
    lateinit var getAskListForMeUseCase: GetAskListForMeUseCase
    lateinit var postAskUseCase: PostAskUseCase
    lateinit var coupleConnectUseCase: PostCoupleConnectUseCase

    var memberId1: Int = 0
    var memberId2: Int = 0

    val memberIdfv1 = "윤성현"
    val memberIdfv2 = "로이"
    val askContent1 = "질문 내용1"
    val askContent2 = "질문 내용2"

    val pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "createdAt")

    @BeforeEach
    fun setUp() {
        val member1 =
            memberRepository.save(
                MemberBuilder(
                    idfv = memberIdfv1,
                ).build(),
            )

        val member2 =
            memberRepository.save(
                MemberBuilder(
                    idfv = memberIdfv2,
                ).build(),
            )

        memberId1 = member1.id
        memberId2 = member2.id

        getAskListForMeUseCase =
            GetAskListForMeAction(
                memberService,
                coupleRepository,
                askRepository,
            )

        postAskUseCase =
            PostAskAction(
                memberService,
                askRepository,
            )

        coupleConnectUseCase =
            PostCoupleConnectAction(
                memberService,
                coupleRepository,
            )
    }

    @Test
    fun `나의 짝꿍이 질문한 리스트는 조회가 가능하다`() {
        coupleConnectUseCase.connect(memberId = memberId1, idfv = memberIdfv2)

        postAskUseCase.ask(
            memberId = memberId1,
            content = askContent1,
        )

        postAskUseCase.ask(
            memberId = memberId1,
            content = askContent2,
        )
        // when
        val askListForMe =
            getAskListForMeUseCase.getAskListForMe(
                memberId = memberId2,
                pageable = pageable,
            )

        // then
        assertThat(askListForMe.totalElement).isEqualTo(2)
        assertThat(askListForMe.items.size).isEqualTo(2)
        assertThat(askListForMe.page).isEqualTo(0)
        assertThat(askListForMe.items).extracting("content").containsExactlyInAnyOrder(askContent1, askContent2)
    }

    @Test
    fun `커플로 맺어지지 않은 멤버가 나를 위한 질문 목록을 조회할 시 예외가 발생한다`() {
        // when & then
        assertThrows<NoCoupleException> {
            getAskListForMeUseCase.getAskListForMe(
                memberId = memberId1,
                pageable = pageable,
            )
        }.message.apply { assertThat("나는 지금 커플로 맺어진 상태가 아닙니다.") }
    }
}
