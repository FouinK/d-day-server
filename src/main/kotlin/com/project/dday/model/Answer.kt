package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer(
    content: String,
    askId: Int,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0

    @Column(name = "content")
    val content: String = content

    @Column(name = "ask_id")
    val askId: Int = askId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member
}
