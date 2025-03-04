package com.shopping.study.util.exception.handler

import com.shopping.study.util.exception.custome.AlreadyLoggedInException
import com.shopping.study.util.exception.dto.ErrorResponseDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {
    companion object {
        const val TRACE = "trace"
        val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @Override
    protected override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        return buildErrorResponse(ex, ex.message ?: "Unexpected error", HttpStatus.valueOf(statusCode.value()),request)
    }

    private fun buildErrorResponse(
        exception: Exception,
        message: String,
        httpStatus: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errorResponseDto = ErrorResponseDto(
            status = httpStatus.value(),
            message = message,
            time = LocalDateTime.now()
        )
        return ResponseEntity.status(httpStatus).body(errorResponseDto)
    }

    @ExceptionHandler(AlreadyLoggedInException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleAlreadyLoggedInException(alreadyLoggedInException: AlreadyLoggedInException, request: WebRequest):
            ResponseEntity<Any> {
        logger.error("Already logged in user", alreadyLoggedInException)
        return buildErrorResponse(alreadyLoggedInException, alreadyLoggedInException.message ?: "Already logged in user", HttpStatus.CONFLICT, request)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllUncaughtException(exception: Exception, request: WebRequest): ResponseEntity<Any> {
        logger.error("Internal error occurred", exception)
        return buildErrorResponse(exception, exception.message ?: "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR, request)
    }
}