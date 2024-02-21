package com.project.dday.controller

import com.project.dday.application.couple.port.`in`.DeleteCoupleDeConnectUseCase
import com.project.dday.application.couple.port.`in`.PostCoupleConnectUseCase
import com.project.dday.dto.CoupleConnectRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/couple")
class CoupleController(
    private val postCoupleConnectUseCase: PostCoupleConnectUseCase,
    private val deleteCoupleDeConnectUseCase: DeleteCoupleDeConnectUseCase,
) {
    @PostMapping
    fun connect(
        @RequestParam("memberId") memberId: Int,
        @RequestBody request: CoupleConnectRequestDto,
    ): ResponseEntity<Any> {
        postCoupleConnectUseCase.connect(
            memberId = memberId,
            idfv = request.idfv,
        )

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deConnect(
        @RequestParam("memberId") memberId: Int,
    ): ResponseEntity<Any> {
        deleteCoupleDeConnectUseCase.deConnect(memberId)
        return ResponseEntity.noContent().build()
    }
}
