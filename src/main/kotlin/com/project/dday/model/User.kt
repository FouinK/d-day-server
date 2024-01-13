package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "idfv", nullable = false, unique = true)
    val idfv: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val askList: List<Ask>? = null,
) : BaseTimeEntity()
