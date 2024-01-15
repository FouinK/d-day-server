package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "ask")
class Ask(
    id: Int? = null,
    content: String,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = id
        private set

    @Column(name = "content")
    var content: String = content
        private set

    @Column(name = "status")
    var status: AskStatus = AskStatus.READY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member
}

enum class AskStatus {
    READY,
    COMPLETE,
}
