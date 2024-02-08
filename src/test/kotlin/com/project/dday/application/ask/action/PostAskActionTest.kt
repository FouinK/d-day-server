package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.exception.NotFoundException
import com.project.dday.fixture.MemberBuilder
import com.project.dday.repository.AskRepository
import com.project.dday.repository.MemberRepository
import com.project.dday.service.MemberService
import org.assertj.core.api.Assertions.*
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
    var memberId1: Int? = null
    var memberId2: Int? = null

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

        postAskUseCase.ask(
            memberId = memberId1!!,
            content = content,
        )
    }

    @Test
    fun `내가 질문한 목록이 정상 저장 되었음을 확인한다`() {
        // given
        val member = memberRepository.findById(memberId1!!)
            .orElseThrow { throw NotFoundException("존재하지 않는 멤버 아아디로 조회하려고 함.") }

        // when
        val askList = askRepository.findByMember(member = member)

        askList.forEach {
            println("it.member : ${it.member.id}")
            println("it.member : ${it.content}")
        }
        // then
        assertThat(askList.size).isEqualTo(1)
        assertThat(askList.first { it.member.id == memberId1 }.content).isEqualTo(content)
    }
}
