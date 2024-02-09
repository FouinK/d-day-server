package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "couple")
class Couple(
    member1: Int,
    member2: Int,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0

    @Column(name = "member1_id")
    var member1: Int = member1
        private set

    @Column(name = "member2_id")
    var member2: Int = member2
        private set
}
