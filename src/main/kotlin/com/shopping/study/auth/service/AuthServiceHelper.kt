package com.shopping.study.auth.service

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.repository.AuthRepository
import com.shopping.study.util.annotations.LogExecutionTime
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

@Service
class AuthServiceHelper(val authRepository: AuthRepository) {
    @LogExecutionTime
    fun checkLoginState(userId: String, request: HttpServletRequest): Boolean {
        // 현재 아이디 값만 확인해서 로그인 여부를 확인하는 중임
        // 음.. 로그인 화면에서 로그인을 시도하는데 아이디만 동일하지 비밀번호가 다르다면..
        // 그때는 세션에 동일한 아이디값이 있다고 해도 로그인 실패로 봐야하지 않을까?
        val session = request.getSession(false)
        return session?.getAttribute("userId") == userId
    }

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