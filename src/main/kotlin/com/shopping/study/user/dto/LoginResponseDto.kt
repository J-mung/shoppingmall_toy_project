package com.shopping.study.user.dto

import java.io.Serializable

data class LoginResponseDto(
    val message: String,
    val user: UserDto? = null
) : Serializable
