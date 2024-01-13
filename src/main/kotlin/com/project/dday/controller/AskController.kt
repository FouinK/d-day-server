package com.project.dday.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/ask")
class AskController {
    @GetMapping
    fun list(): ResponseEntity<Any> {
        return ResponseEntity.ok("")
    }

    @PostMapping
    fun ask(): ResponseEntity<Any> {
        return ResponseEntity.ok("")
    }
}
