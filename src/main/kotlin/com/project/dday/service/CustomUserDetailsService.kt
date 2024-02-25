package com.project.dday.service

import com.project.dday.model.MemberCustomUserDetails
import com.project.dday.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
) : UserDetailsService {
    override fun loadUserByUsername(memberId: String): UserDetails {
        val member =
            memberRepository.findById(memberId.toInt())
                .orElseThrow { throw UsernameNotFoundException(memberId + "가 존재하지 않습니다") }

        try {
            return MemberCustomUserDetails(
                custId = member.id.toLong(),
                loginId = member.idfv,
                loginPassword = member.idfv,
            )
        } catch (e: Exception) {
            throw UsernameNotFoundException(memberId + "가 존재하지 않습니다")
        }
    }
}
