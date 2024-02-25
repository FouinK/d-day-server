package com.project.dday.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.dday.config.dto.CurrentMember
import com.project.dday.config.dto.ErrorDetailsDto
import com.project.dday.repository.MemberSessionRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime

class AuthFilter(
    private val memberSessionRepository: MemberSessionRepository,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val uri = request.requestURI

        if (!uri.startsWith("/myTestApp/server/v1/member")) {
            filterChain.doFilter(request, response)
            return
        }

        val requestSessionId = request.getHeader("Authorization")

        if (requestSessionId == null) {
            val responseDto =
                ErrorDetailsDto(
                    errorMessage = "인증되지 않았습니다.",
                    timeStamp = LocalDateTime.now().toString(),
                )

            errorHandler(response, responseDto)
            return
        }

        val memberSession = memberSessionRepository.findById(requestSessionId)

        if (memberSession.isEmpty) {
            val responseDto =
                ErrorDetailsDto(
                    errorMessage = "인증되지 않았습니다.",
                    timeStamp = LocalDateTime.now().toString(),
                )

            errorHandler(response, responseDto)
            return
        }

        val currentMember = CurrentMember(memberSession.get().memberId)

        request.setAttribute("currentMember", currentMember)

        filterChain.doFilter(request, response)
    }

    private fun errorHandler(
        response: HttpServletResponse,
        responseDto: ErrorDetailsDto,
    ) {
        response.contentType = "application/json;charset=UTF-8"
        response.status = 401
        response.writer.write(objectMapper.writeValueAsString(responseDto))
    }
}
