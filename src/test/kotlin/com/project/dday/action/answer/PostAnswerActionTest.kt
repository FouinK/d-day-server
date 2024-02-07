package com.project.dday.action.answer

import com.project.dday.application.ask.action.PostAskAction
import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.repository.AnswerRepository
import com.project.dday.repository.AskRepository
import com.project.dday.service.AskService
import com.project.dday.service.CoupleService
import com.project.dday.service.MemberService
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
) {
    lateinit var postAskUseCase: PostAskUseCase

    @BeforeEach
    fun setUp() {
        postAskUseCase = PostAskAction(
            memberService,
            askRepository,
        )

//        postAskUseCase.ask()
    }

    @Test
    fun `여기`() {
        // given

        // when

        // then
    }
}
