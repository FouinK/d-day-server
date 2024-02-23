// package com.project.dday.config
//
// import com.fasterxml.jackson.databind.ObjectMapper
// import com.project.dday.config.dto.ErrorDetailsDto
// import jakarta.servlet.FilterChain
// import jakarta.servlet.http.HttpServletRequest
// import jakarta.servlet.http.HttpServletResponse
// import org.springframework.web.filter.OncePerRequestFilter
// import java.time.LocalDateTime
//
// class AuthFilter(
//    val objectMapper: ObjectMapper,
// ) : OncePerRequestFilter() {
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain,
//    ) {
//        val uri = request.requestURI
//
//        if (!uri.startsWith("/api/ch")) {
//            filterChain.doFilter(request, response)
//            return
//        }
//
//        val requestSessionId = request.getHeader("Authorization")
//
//        if (requestSessionId == null) {
//            val responseDto =
//                ErrorDetailsDto(
//                    errorMessage = "로그인이 필요한 요청입니다.",
//                    timeStamp = LocalDateTime.now().toString(),
//                )
//
//            errorHandler(response, responseDto)
//            return
//        }
//
//        if (uri.startsWith("/api/ch/mallMember")) {
//            val mallMemberSession = mallMemberSessionRepository.findById(requestSessionId)
//
//            if (mallMemberSession.isEmpty) {
//                val responseDto =
//                    ErrorDetailsDto(
//                        errorMessage = "로그인이 필요한 요청입니다.",
//                        timeStamp = LocalDateTime.now().toString(),
//                    )
//
//                errorHandler(response, responseDto)
//                return
//            }
//
//            val currentMember = CurrentMember(mallMemberSession.get().memberId)
//
//            request.setAttribute("currentMember", currentMember)
//
//            filterChain.doFilter(request, response)
//        } else {
//            val custSession = custSessionRepository.findById(requestSessionId)
//
//            if (custSession.isEmpty) {
//                val responseDto =
//                    ErrorDetailsDto(
//                        errorMessage = "로그인이 필요한 요청입니다.",
//                        timeStamp = LocalDateTime.now().toString(),
//                    )
//
//                errorHandler(response, responseDto)
//                return
//            }
//
//            val currentCust = CurrentCust(custSession.get().custId)
//
//            request.setAttribute("currentCust", currentCust)
//
//            filterChain.doFilter(request, response)
//        }
//    }
//
//    private fun errorHandler(
//        response: HttpServletResponse,
//        responseDto: ErrorDetailsDto,
//    ) {
//        response.contentType = "application/json;charset=UTF-8"
//        response.status = 401
//        response.writer.write(objectMapper.writeValueAsString(responseDto))
//    }
// }
