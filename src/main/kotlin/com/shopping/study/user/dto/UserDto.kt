package com.shopping.study.user.dto

import java.io.Serializable

data class UserDto(
    val id: Long,
    val userId: String,
    val email: String,
    val userName: String
): Serializable
