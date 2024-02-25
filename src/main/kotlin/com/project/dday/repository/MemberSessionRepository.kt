package com.project.dday.repository

import com.project.dday.model.MemberSession
import org.springframework.data.repository.CrudRepository

interface MemberSessionRepository : CrudRepository<MemberSession, String> {
    fun findByMemberId(memberId: Int): MemberSession?
}
