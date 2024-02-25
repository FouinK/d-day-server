package com.project.dday.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("mallMemberSession")
@TypeAlias("mallMemberSession")
class MemberSession(
    @Id
    var id: String,
    @Indexed
    var memberId: Int,
)
