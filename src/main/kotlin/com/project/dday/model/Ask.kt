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
