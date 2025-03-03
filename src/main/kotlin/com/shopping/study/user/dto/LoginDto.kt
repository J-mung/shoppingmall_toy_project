package com.shopping.study.user.dto

import java.io.Serializable

data class LoginDto(
    val userId: String,
    val passwd: String? = null,
    val message: String? = null,
    val user: UserDto? = null
) : Serializable
