package com.project.dday.service

import com.project.dday.model.MemberCustomUserDetails
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
@Primary
class CustomAuthenticationManagerService(
    private val customUserDetailsService: CustomUserDetailsService,
) : AuthenticationManager {
    override fun authenticate(authentication: Authentication): Authentication {
        val loginId = authentication.name

        val user = customUserDetailsService.loadUserByUsername(loginId) as MemberCustomUserDetails

        return UsernamePasswordAuthenticationToken(
            loginId,
            user.custId,
            user.authorities,
        )
    }
}
