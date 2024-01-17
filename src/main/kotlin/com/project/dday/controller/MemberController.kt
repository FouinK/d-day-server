package com.project.dday.user

import com.project.dday.action.member.PostJoinAction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/myTestApp/server/v1/user")
class MemberController(
    private val postJoinAction: PostJoinAction,
) {
    @PostMapping
    fun join(
        @RequestParam("idfv") idfv: String,
    ): ResponseEntity<Any> {
        postJoinAction.join(idfv)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
