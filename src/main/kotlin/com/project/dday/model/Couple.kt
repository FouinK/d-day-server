package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "couple")
class Couple(
    member1Id: Int,
    member2Id: Int,
    deConnect: Boolean = false,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0

    @Column(name = "member1_id")
    var member1Id: Int = member1Id
        private set

    @Column(name = "member2_id")
    var member2Id: Int = member2Id
        private set

    @Column(name = "deConnect", nullable = false)
    var deConnect: Boolean = deConnect
        private set
}
