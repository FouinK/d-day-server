package com.project.dday.user

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first/server/v1/member")
class MemberController(
    private val postJoinUseCase: PostJoinUseCase,
) {
    @PostMapping
    fun join(
        @RequestParam("idfv") idfv: String,
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.CREATED).body(postJoinUseCase.join(idfv))
    }
}
