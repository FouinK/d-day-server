package com.project.dday.controller

import com.project.dday.service.CoupleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/couple")
class CoupleController(
    private val coupleService: CoupleService,
) {
    @PostMapping
    fun connect(
        @RequestParam("memeberId") memberId: Int,
        @RequestParam("idfv") idfv: String,
    ): ResponseEntity<Any> {
        coupleService.connect(
            memberId = memberId,
            idfv = idfv,
        )

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
