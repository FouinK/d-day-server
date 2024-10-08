package com.project.dday.application.ask.action

import com.project.dday.application.ask.port.`in`.GetAskListForMeUseCase
import com.project.dday.dto.AskListForMeResponseDto
import com.project.dday.exception.NoCoupleException
import com.project.dday.repository.AskRepository
import com.project.dday.repository.CoupleRepository
import com.project.dday.service.MemberService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetAskListForMeAction(
    private val memberService: MemberService,
    private val coupleRepository: CoupleRepository,
    private val askRepository: AskRepository,
) : GetAskListForMeUseCase {
    override fun getAskListForMe(
        memberId: Int,
        pageable: Pageable,
    ): AskListForMeResponseDto {
        val couple =
            coupleRepository.findByMember1Id(memberId)
                ?: throw NoCoupleException("나는 지금 커플로 맺어진 상태가 아닙니다.")

        val myCoupleMemberId = if (couple.member1Id == memberId) couple.member2Id else couple.member1Id

        val myCoupleMember = memberService.validateMember(myCoupleMemberId)

        val askListForMe = askRepository.findAllByMember(myCoupleMember, pageable)

        return AskListForMeResponseDto(
            page = askListForMe.number,
            totalElement = askListForMe.totalElements.toInt(),
            items =
                askListForMe.content.map {
                    AskListForMeResponseDto.Item(
                        content = it.content,
                        status = it.status,
                    )
                },
        )
    }
}
