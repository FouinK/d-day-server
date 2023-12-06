package com.project.dday.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/")
    fun firstMapping(): ResponseEntity<Any> {
        return ResponseEntity.ok("테스트 완료")
    }
}
