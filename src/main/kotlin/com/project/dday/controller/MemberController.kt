package com.project.dday.user

import com.project.dday.application.member.port.`in`.PostJoinUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/user")
class MemberController(
    private val postJoinUseCase: PostJoinUseCase,
) {
    @PostMapping
    fun join(
        @RequestParam("idfv") idfv: String,
    ): ResponseEntity<Any> {
        postJoinUseCase.join(idfv)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
