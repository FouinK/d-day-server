package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Column(name = "idfv", nullable = false, unique = true)
    val idfv: String,

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    val askList: List<Ask>? = null,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0
}
