package com.shopping.study.user.service

import com.shopping.study.user.entity.UserEntity
import com.shopping.study.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUserById(id: Long): Optional<UserEntity> {
        return userRepository.findById(id)
    }

    fun getUserByUserId(userId: String): Optional<UserEntity> {
        return userRepository.findByUserId(userId)
    }

    fun createUser(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}