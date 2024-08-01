package com.project.dday.user

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import com.project.dday.dto.JoinRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first/server/v1/member")
class MemberController(
    private val postJoinUseCase: PostJoinUseCase,
) {
    @PostMapping
    fun join(
        @RequestBody request: JoinRequestDto,
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(postJoinUseCase.join(request.idfv))
    }
}
