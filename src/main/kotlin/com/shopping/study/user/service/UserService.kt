package com.shopping.study.user.service

import com.shopping.study.user.entity.UserEntity
import com.shopping.study.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun authenticate(userId: String, passwd: String): Boolean {
        val user = userRepository.findByUserId(userId)
        return user.isPresent && user.get().passwd == passwd
    }
}