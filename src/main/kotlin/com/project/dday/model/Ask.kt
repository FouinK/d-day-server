package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "ask")
class Ask(
    content: String,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0

    @Column(name = "content")
    var content: String = content
        private set

    @Column(name = "status", columnDefinition = "ENUM('READY','COMPLETE')")
    @Enumerated(EnumType.STRING)
    var status: AnswerStatus = AnswerStatus.READY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member

    fun answer() {
        this.status = AnswerStatus.COMPLETE
    }
}

enum class AnswerStatus {
    READY,
    COMPLETE,
}
