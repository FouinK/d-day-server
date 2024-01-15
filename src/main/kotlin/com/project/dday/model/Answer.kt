package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer(
    id: Int? = null,
    content: String,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = id
        private set

    @Column(name = "content")
    val content: String = content

    @Column(name = "status")
    var status: AnswerStatus = AnswerStatus.READY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member
}

enum class AnswerStatus {
    READY,
    COMPLETE,
}
