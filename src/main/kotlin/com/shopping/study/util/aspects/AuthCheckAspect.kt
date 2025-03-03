package com.shopping.study.util.aspects

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.service.AuthServiceHelper
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthCheckAspect(
    private val request: HttpServletRequest,
    private val helper: AuthServiceHelper,
) {

    @Around("@annotation(com.shopping.study.util.annotations.AuthCheck)")
    fun checkLoginState(joinPoint: ProceedingJoinPoint): Any {
        logger.info("> 로그인 상태 체크 실행: {}", joinPoint.signature.name)

        val sessionUserId = request.session.getAttribute("userId") as? String
        logger.info("> userId: {}", sessionUserId ?: "There is no userId in session")

        if (sessionUserId != null && helper.checkLoginState(sessionUserId, request)) {
            return ResponseEntity.ok(
                mapOf(
                    "message" to "Already login",
                    "userId" to sessionUserId
                )
            )
        }

        val loginDto = joinPoint.args.find { it is loginDto } as? loginDto
        logger.info("> 로그인 필요한 사용자: {}", loginDto?.userId)

        return joinPoint.proceed()
    }

    companion object {
        val logger = LoggerFactory.getLogger(AuthCheckAspect::class.java)
    }
}