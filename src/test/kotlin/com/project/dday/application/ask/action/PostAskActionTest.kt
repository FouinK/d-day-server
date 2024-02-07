package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.repository.AskRepository
import com.project.dday.service.MemberService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostAskActionTest(
    @Autowired val memberService: MemberService,
    @Autowired val askRepository: AskRepository,
) {
    lateinit var postAskUseCase: PostAskUseCase

    @BeforeEach
    fun setUp() {
        postAskUseCase = PostAskAction(
            memberService,
            askRepository,
        )

//        postAskUseCase.ask(
//
//        )
    }

    @Test
    fun `여기`() {
        // given

        // when

        // then
    }
}
