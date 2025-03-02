package com.shopping.study.auth.service

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.dto.logoutDto
import com.shopping.study.auth.repository.AuthRepository
import com.shopping.study.util.annotations.LogExecutionTime
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    val authRepository: AuthRepository,
    val helper : AuthServiceHelper
) {
    @LogExecutionTime
    fun login(loginDto: loginDto, request: HttpServletRequest): ResponseEntity<Any> {
        if (helper.checkLoginState(loginDto.userId, request)) {
            return ResponseEntity.ok(mapOf(
                "message" to "Already login",
                "userId" to loginDto.userId
            ))
        }

        val authResult = helper.authentication(loginDto)

        return when (authResult) {
            true -> {
                val session = request.getSession()
                session.setAttribute("userId", loginDto.userId)

                ResponseEntity.ok(mapOf(
                    "message" to "Login success!",
                    "userId" to loginDto.userId
                ))
            }

            else -> {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(mapOf(
                        "message" to "Login failed."
                    ))
            }
        }
    }

    @LogExecutionTime
    fun logout(logoutDto: logoutDto, request: HttpServletRequest): ResponseEntity<Map<String, String>> {
        if (helper.checkLoginState(logoutDto.userId, request)) {
            request.getSession().removeAttribute("userId")

            return ResponseEntity.ok().body(mapOf(
                "message" to "Logout success!",
                "userId" to logoutDto.userId
            ))
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf(
                "message" to "Invalid logout request"
            ))
        }
    }
}