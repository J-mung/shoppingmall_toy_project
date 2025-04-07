package com.shopping.study.user.dto

import java.io.Serializable

data class LoginRequestDto(
    val userId: String,
    val passwd: String
) : Serializable
