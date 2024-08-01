package com.project.dday.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.dday.repository.MemberSessionRepository
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(
    val memberSessionRepository: MemberSessionRepository,
    val objectMapper: ObjectMapper,
) {
    @Bean
    fun authFilter(): FilterRegistrationBean<AuthFilter> {
        val registrationBean = FilterRegistrationBean<AuthFilter>()
        registrationBean.filter = AuthFilter(memberSessionRepository, objectMapper)
        registrationBean.addUrlPatterns("/myTestApp/*")
        return registrationBean
    }
}
