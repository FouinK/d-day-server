package com.project.dday.dto

import com.project.dday.model.AnswerStatus

class AskListForMeResponseDto(
    val page: Int,
    val totalElement: Int,
    val items: List<Item>,
) {
    class Item(
        val content: String,
        val status: AnswerStatus,
    )
}
