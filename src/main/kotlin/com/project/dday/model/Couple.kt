package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "couple")
class Couple(
    id: Int? = null,
    user1: Int,
    user2: Int,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = id
        private set

    @Column(name = "user1_id")
    var user1: Int = user1
        private set

    @Column(name = "user2_id")
    var user2: Int = user2
        private set
}
