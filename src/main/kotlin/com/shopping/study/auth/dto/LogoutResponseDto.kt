package com.shopping.study.auth.dto

import org.springframework.http.HttpStatus
import java.io.Serializable

data class LogoutResponseDto(
    val status: HttpStatus,
    val errorCode: String,
    val data: String
): Serializable
