package com.project.dday.repository

import com.project.dday.model.Couple
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleRepository : JpaRepository<Couple, Int> {
    fun existsByMember1Id(member1Id: Int): Boolean

    fun findByMember1Id(member1Id: Int): Couple?
}
