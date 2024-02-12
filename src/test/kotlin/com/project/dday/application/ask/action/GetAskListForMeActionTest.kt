package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.GetAskListForMeUseCase
import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.application.couple.action.PostCoupleConnectAction
import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.repository.AskRepository
import com.project.dday.repository.CoupleRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
// given
        val member1 =
            memberRepository.findById(memberId1)
                .orElseThrow { throw NotFoundException("멤버가 없습니다. 테스트 실패") }

        // when
        coupleConnectUseCase.connect(memberId = member1.id, idfv = memberIdfv2)

        val askResponseDto1 =
            postAskUseCase.ask(
                memberId = memberId1,
                content = askContent1,
            )
        val askResponseDto2 =
            postAskUseCase.ask(
                memberId = memberId1,
                content = askContent2,
            )

        // then
    }

    @Test
    fun `완전히 다른 사람이 질문한 목록을 조회하려고 하면 예외가 발생한다`() {
        // given

        // when

        // then
    }
}
