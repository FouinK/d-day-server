package com.project.dday.user

import com.project.dday.application.ask.port.`in`.GetAskListForMeUseCase
import com.project.dday.application.ask.port.`in`.GetMyAskListUseCase
import com.project.dday.application.ask.port.`in`.PostAskUseCase
import com.project.dday.dto.AskListResponseDto
import com.project.dday.dto.AskRequestDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/ask")
class AskController(
    private val getMyAskListUseCase: GetMyAskListUseCase,
    private val getAskListForMeUseCase: GetAskListForMeUseCase,
    private val postAskUseCase: PostAskUseCase,
) {
    // TODO : answer이랑 ask 둘다 테이블에서 member_id 관계 끊기
    @GetMapping
    fun getMyAskList(
        @RequestParam("memberId") memberId: Int,
        @PageableDefault(size = 10, page = 0, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable,
    ): ResponseEntity<Any> {
        val response =
            getMyAskListUseCase.getMyAskList(
                memberId = memberId,
                pageable = pageable,
            )
        return ResponseEntity.ok(
            AskListResponseDto(
                page = response.totalPages,
                totalElement = response.totalElements.toInt(),
                items =
                    response.content.map {
                        AskListResponseDto.Item(
                            content = it.content,
                            status = it.status,
                        )
                    },
            ),
        )
    }

    @GetMapping
    fun getAskListForMe(
        @RequestParam("memberId") memberId: Int,
        @PageableDefault(size = 10, page = 0, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable,
    ): ResponseEntity<Any> {
        getAskListForMeUseCase.getAskListForMe()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping
    fun ask(
        @RequestParam("memberId") memberId: Int,
        @RequestBody request: AskRequestDto,
    ): ResponseEntity<Any> {
        postAskUseCase.ask(
            memberId = memberId,
            content = request.content,
        )
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
