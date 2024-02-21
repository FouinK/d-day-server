package com.project.dday.application.couple.action

import com.project.dday.application.couple.port.`in`.DeleteCoupleDeConnectUseCase
import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class DeleteCoupleDeConnectActionTest(
    @Autowired val coupleRepository: CoupleRepository,
    @Autowired val memberRepository: MemberRepository,
    @Autowired val memberService: MemberService,
) {
    lateinit var deleteCoupleDeConnectUseCase: DeleteCoupleDeConnectUseCase
    lateinit var postCoupleConnectUseCase: PostCoupleConnectUseCase

    var memberId1 = 0
    var memberId2 = 0

    val idfv1 = "로이"
    val idfv2 = "윤성현"

    @BeforeEach
    fun setUp() {
        val member1 =
            MemberBuilder(
                idfv = idfv1,
            ).build()

        val member2 =
            MemberBuilder(
                idfv = idfv2,
            ).build()

        memberRepository.saveAll(
            listOf(
                member1,
                member2,
            ),
        )

        memberId1 = member1.id
        memberId2 = member2.id

        deleteCoupleDeConnectUseCase =
            DeleteCoupleDeConnectAction(
                coupleRepository,
            )

        postCoupleConnectUseCase =
            PostCoupleConnectAction(
                memberService,
                coupleRepository,
            )

        postCoupleConnectUseCase.connect(memberId1, idfv2)
    }

    @Test
    fun `커플 관계가 정상 끊어지는 것을 확인한다`() {
        // when
        deleteCoupleDeConnectUseCase.deConnect(memberId1)

        val couple1 =
            coupleRepository.findByMember1Id(memberId1)
                ?: throw NotFoundException("커플이 존재하지 않음 테스트 실패")

        val couple2 =
            coupleRepository.findByMember1Id(memberId2)
                ?: throw NotFoundException("커플이 존재하지 않음 테스트 실패")

        // then
        assertThat(couple1.deConnect).isTrue()
        assertThat(couple2.deConnect).isTrue()
    }
}
