package com.project.dday.repository

import com.project.dday.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Int> {
    fun existsByIdfv(idfv: String): Boolean

    fun findByIdfv(idfv: String): Member?
}
