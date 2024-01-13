package com.project.dday.service

import com.project.dday.model.User
import com.project.dday.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun join(idfv: String) {
        val newUser = User(
            idfv = idfv,
        )
        userRepository.save(newUser)
    }
}
