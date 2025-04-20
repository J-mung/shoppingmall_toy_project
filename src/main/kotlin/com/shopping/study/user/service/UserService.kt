package com.shopping.study.user.service

import UnauthorizedException
import com.shopping.study.user.dto.LoginRequestDto
import com.shopping.study.user.dto.LoginResponseDto
import com.shopping.study.user.mapper.UserMapper
import com.shopping.study.user.repository.UserRepository
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val session: HttpSession,
    private val userMapper: UserMapper
) {
    fun authenticate(loginRequestDto: LoginRequestDto): LoginResponseDto {
        // 사용자 조회, 없으면 빠른 실패 처리
        val user = userRepository.findByUserId(loginRequestDto.userId)
            ?: throw UnauthorizedException("사용자를 찾을 수 없습니다.")

        // 비밀번호 검증 실패 시 빠른 실패 처리
        if (user.passwd != loginRequestDto.passwd) {
            throw UnauthorizedException("비밀번호가 일치하지 않습니다.")
        }

        val userDto = userMapper.toDto(user)
        session.setAttribute("userId", userDto.userId)
        return LoginResponseDto(
            message = "로그인 성공",
            user = userDto
        )
    }
}
