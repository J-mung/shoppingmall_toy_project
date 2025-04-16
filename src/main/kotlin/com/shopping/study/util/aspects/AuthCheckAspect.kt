package com.shopping.study.util.aspects

import com.shopping.study.auth.dto.LoginResponseDto
import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.service.AuthService
import com.shopping.study.util.exception.custome.AlreadyLoggedInException
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthCheckAspect(
    private val request: HttpServletRequest,
    private val authService: AuthService,
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(AuthCheck)")
    fun checkLoginState(joinPoint: ProceedingJoinPoint): Any {
        logger.info("> 로그인 상태 체크 실행: {}", joinPoint.signature.name)

        val sessionUserId = request.session.getAttribute("userId") as? String
        logger.info("> userId: {}", sessionUserId ?: "There is no userId in session")

        if (sessionUserId != null && authService.checkLoginState(sessionUserId, request)) {
            return ResponseEntity.ok()
                .body(LoginResponseDto(
                    status = HttpStatus.BAD_REQUEST,
                    errorCode = "Already login",
                    data = sessionUserId
                ))
        }

        val loginDto = joinPoint.args.find { it is loginDto } as? loginDto
        logger.info("> 로그인 필요한 사용자: {}", loginDto?.userId)

        return joinPoint.proceed()
    }

    @Before("@annotation(com.shopping.study.util.annotations.CheckLoginState)")
    fun checkLoginState(joinPoint: JoinPoint) {
        logger.info("> 로그인 상태 체크 실행: {}", joinPoint.signature.name)

        val sessionUserId = request.session.getAttribute("userId") as? String
        logger.info("> userId: {}", sessionUserId ?: "There is no userId in session")

        if (sessionUserId != null && authService.checkLoginState(sessionUserId, request)) {
            throw AlreadyLoggedInException("이미 로그인한 사용자")
        }

        val loginDto = joinPoint.args.find { it is loginDto } as? loginDto
        logger.info("> 로그인 필요한 사용자: {}", loginDto?.userId)
    }
}