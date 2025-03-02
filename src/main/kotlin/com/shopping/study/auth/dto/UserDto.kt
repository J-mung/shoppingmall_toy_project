package com.shopping.study.auth.dto

import java.io.Serializable

data class UserDto(
    val id: Int,
    var userId: String,
    var email: String,
    var password: String,
    var userName: String
) : Serializable
