package com.shopping.study.auth.dto

import java.io.Serializable

data class LogoutResponseDto(
    val message: String,
    val userId: String
): Serializable
