package com.shopping.study.user.service

import com.shopping.study.user.dto.LoginDto
import com.shopping.study.user.entity.UserEntity
import com.shopping.study.user.repository.UserRepository
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val session: HttpSession
) {

    fun authenticate(loginDto: LoginDto): LoginDto {
        val user = userRepository.findByUserId(loginDto.userId)

        return if (user.isPresent && user.get().passwd == loginDto.passwd) {
            val userDto = user.get().toDto()
            session.setAttribute("userId", userDto.userId)
            LoginDto(
                userId = loginDto.userId,
                message = "로그인 성공",
                user = userDto
            )
        } else {
            LoginDto(
                userId = loginDto.userId,
                message = "로그인 실패"
            )
        }
    }
}