package com.shopping.study.auth.service

import com.shopping.study.auth.dto.LoginResponseDto
import com.shopping.study.auth.dto.LogoutResponseDto
import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.dto.logoutDto
import com.shopping.study.user.service.UserService
import com.shopping.study.util.annotations.LogExecutionTime
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    val userService: UserService
) {

    @LogExecutionTime
    fun login(loginDto: loginDto, request: HttpServletRequest): ResponseEntity<LoginResponseDto> {
        val authResult = userService.authentication(loginDto)

        return when (authResult) {
            true -> {
                val session = request.getSession()
                session.setAttribute("userId", loginDto.userId)
                session.setMaxInactiveInterval(60 * 30);

                ResponseEntity.ok(LoginResponseDto (
                    message = "Login success!",
                    userId = loginDto.userId
                ))
            }

            else -> {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(LoginResponseDto (
                        message = "Login failed",
                        userId = "-"
                    ))
            }
        }
    }

    @LogExecutionTime
    fun logout(logoutDto: logoutDto, request: HttpServletRequest): ResponseEntity<LogoutResponseDto> {
        if (checkLoginState(logoutDto.userId, request)) {
            request.getSession().removeAttribute("userId")

            return ResponseEntity.ok().body(LogoutResponseDto (
                message = "Logout success!",
                userId = logoutDto.userId
            ))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(LogoutResponseDto (
                    message = "Invalid logout request",
                    userId = "-"
                ))
        }
    }

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
}