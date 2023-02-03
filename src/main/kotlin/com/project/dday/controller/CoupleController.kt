package com.project.dday.controller

import com.project.dday.application.couple.action.PostCoupleConnectAction
import com.project.dday.dto.CoupleConnectRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/couple")
class CoupleController(
    private val postCoupleConnectAction: PostCoupleConnectAction,
) {
    @PostMapping
    fun connect(
        @RequestParam("memberId") memberId: Int,
        @RequestBody request: CoupleConnectRequestDto,
    ): ResponseEntity<Any> {
        postCoupleConnectAction.connect(
            memberId = memberId,
            idfv = request.idfv,
        )

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
