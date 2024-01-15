package com.project.dday.model

import jakarta.persistence.*

@Entity
@Table(name = "answer")
class Answer(
    id: Int? = null,
    content: String,
    askId: Int,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = id
        private set

    @Column(name = "content")
    val content: String = content

    @Column(name = "ask_id")
    val askId: Int = askId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member
}
