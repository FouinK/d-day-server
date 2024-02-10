package com.project.dday.repository

import com.project.dday.model.Ask
import com.project.dday.model.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AskRepository : JpaRepository<Ask, Int> {
    fun findAllByMember(
        member: Member,
        pageable: Pageable,
    ): Page<Ask>

    fun findByMember(member: Member): List<Ask>
}
