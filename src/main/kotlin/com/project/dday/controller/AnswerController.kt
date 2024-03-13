package com.project.dday.controller

import com.project.dday.application.answer.port.`in`.GetAnswerListUseCase
import com.project.dday.application.answer.port.`in`.PostAnswerUseCase
import com.project.dday.config.annotation.Member
import com.project.dday.config.dto.CurrentMember
import com.project.dday.dto.AnswerRequestDto
import com.project.dday.dto.AnswerResponseDto
import com.project.dday.model.AnswerStatus
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/answer")
class AnswerController(
    private val getAnswerListUseCase: GetAnswerListUseCase,
    private val postAnswerUseCase: PostAnswerUseCase,
) {
    @GetMapping
    fun list(
        @Member currentMember: CurrentMember,
        @PageableDefault(size = 10, page = 0, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable,
    ): ResponseEntity<Any> {
        val response =
            getAnswerListUseCase.getAnswerList(
                memberId = currentMember.memberId,
                pageable = pageable,
            )

        return ResponseEntity.ok(
            AnswerResponseDto(
                page = response.totalPages,
                totalElement = response.totalElements.toInt(),
                items =
                    response.content.map {
                        AnswerResponseDto.Item(
                            content = it.content,
                            status = AnswerStatus.READY, // TODO : 수정해야함
                        )
                    },
            ),
        )
    }

    @PostMapping
    fun answer(
        @Member currentMember: CurrentMember,
        @RequestBody request: AnswerRequestDto,
    ): ResponseEntity<Any> {
        postAnswerUseCase.answer(
            memberId = currentMember.memberId,
            content = request.content,
            askId = request.askId,
        )

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
