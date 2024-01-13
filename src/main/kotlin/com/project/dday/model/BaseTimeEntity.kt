package com.project.dday.model

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import java.time.LocalDateTime

@MappedSuperclass
open class BaseTimeEntity {

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null
        private set

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        private set

    @PrePersist
    fun onPrePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun onPreUpdate() {
        updatedAt = LocalDateTime.now()
    }
}
