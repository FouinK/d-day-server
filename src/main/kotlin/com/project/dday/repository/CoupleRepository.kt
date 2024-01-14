package com.project.dday.repository

import com.project.dday.model.Couple
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleRepository : JpaRepository<Couple, Int> {
    fun existsByMember1OrMember2(member1: Int, member2: Int): Boolean
}
