@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.project.dday.application.couple.action

import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.CoupleConnectException
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostCoupleConnectActionTest(
    @Autowired val memberService: MemberService,
    @Autowired val coupleRepository: CoupleRepository,
    @Autowired val memberRepository: MemberRepository,
) {
    lateinit var postCoupleConnectUseCase: PostCoupleConnectUseCase

    var memberId1: Int = 0
    var memberId2: Int = 0

    val memberIdfv1 = "윤성현"
    val memberIdfv2 = "로이"

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

        postCoupleConnectUseCase =
            PostCoupleConnectAction(
                memberService,
                coupleRepository,
            )
    }

    @Test
    fun `커플이 정상 맺어졌는지 확인한다`() {
        // when
        postCoupleConnectUseCase.connect(
            memberId = memberId1,
            idfv = memberIdfv2,
        )

        // then
        val couple1 =
            coupleRepository.findByMember1Id(memberId1)
                ?: throw NotFoundException("커플이 제대로 맺어지지 않았습니다. 테스트 실패")
        val couple2 =
            coupleRepository.findByMember1Id(memberId2)
                ?: throw NotFoundException("커플이 제대로 맺어지지 않았습니다. 테스트 실패")

        assertThat(memberId1).isEqualTo(couple2.member2Id)
        assertThat(memberId2).isEqualTo(couple1.member2Id)
    }

    @Test
    fun `자기 자신을 커플로 등록할 시 예외가 발생한다`() {
        // when & then
        assertThrows<CoupleConnectException> {
            postCoupleConnectUseCase.connect(
                memberId = memberId1,
                idfv = memberIdfv1,
            )
        }.message.apply { assertThat("자기 자신과 커플을 맺을 수 없습니다.") }
    }

    @Test
    fun `이미 커플로 맺어진 사람이 커플 맺음 시도를 하면 예외가 발생한다`() {
        // given
        postCoupleConnectUseCase.connect(
            memberId = memberId1,
            idfv = memberIdfv2,
        )

        // when & then
        assertThrows<CoupleConnectException> {
            postCoupleConnectUseCase.connect(
                memberId = memberId1,
                idfv = memberIdfv2,
            )
        }.message.apply { assertThat("당신은 이미 커플이 맺어진 상태입니다.") }
    }
}
