package com.shopping.study.user.service

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.repository.AuthRepository
import com.shopping.study.util.annotations.LogExecutionTime
import org.springframework.stereotype.Service

@Service
class UserService (
    val authRepository: AuthRepository
) {
    @LogExecutionTime
    fun authentication(loginDto: loginDto): Boolean {
        val user = authRepository.findByUserId(loginDto.userId)

        if (user !== null && user.passwd == loginDto.userPw) {
            return true
        } else {
            return false
        }
    }
}