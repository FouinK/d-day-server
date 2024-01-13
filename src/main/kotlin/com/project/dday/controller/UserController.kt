package com.project.dday.user

import com.project.dday.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/user")
class UserController(
    private val userService: UserService,
) {
    @PostMapping
    fun join(
        @RequestParam("idfv") idfv: String,
    ): ResponseEntity<Any> {
        userService.join(idfv)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
