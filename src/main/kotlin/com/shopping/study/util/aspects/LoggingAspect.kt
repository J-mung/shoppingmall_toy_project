package com.shopping.study.util.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Around("@annotation(com.shopping.study.util.annotations.LogExecutionTime)")
    @Throws(Throwable::class)
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()

        logger.info("> 실행: {}{}", joinPoint.signature.name, joinPoint.args)

        val result = joinPoint.proceed()
        val endTime = System.currentTimeMillis()

        logger.info("> 완료: {} 실행시간 = {}ms", joinPoint.signature.name, (endTime - startTime))

        return result
    }
}