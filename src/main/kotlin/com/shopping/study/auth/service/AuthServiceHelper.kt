package com.shopping.study.auth.service

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.repository.AuthRepository
import com.shopping.study.util.annotations.AuthCheck
import com.shopping.study.util.annotations.LogExecutionTime
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service

/**
 * session 로그인에 필요한 인증을 처리하는 service helper
 */
@Service
class AuthServiceHelper(val authRepository: AuthRepository) {
    /**
     * @param userId : 로그인을 시도한 사용자 ID
     * @param request : http servlet request
     *
     * session에 있는 데이터를 조회해서 로그인 상태 확인
     */
    @LogExecutionTime
    fun checkLoginState(userId: String, request: HttpServletRequest): Boolean {
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