package com.shopping.study.user.dto

import java.io.Serializable

data class LoginResponseDto(
    val userId: String,
    val message: String,
    val user: UserDto? = null
) : Serializable
