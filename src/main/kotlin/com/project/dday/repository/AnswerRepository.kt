package com.project.dday.repository

import com.project.dday.model.Answer
import com.project.dday.model.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Int> {
    fun findByMember(member: Member, pageable: Pageable): Page<Answer>

    fun existsByAskId(askId: Int): Boolean
}
