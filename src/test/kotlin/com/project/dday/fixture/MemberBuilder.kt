package com.project.dday.fixture

import com.project.dday.model.Ask
import com.project.dday.model.Member

class MemberBuilder(
    val id: Int? = null,
    val idfv: String,
    val askList: List<Ask>? = null,
) {
    fun build() = Member(
        idfv = idfv,
    )
}
