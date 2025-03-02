package com.shopping.study.auth.dto

import java.io.Serializable

data class loginDto(
    val userId: String,
    val userPw: String
): Serializable
