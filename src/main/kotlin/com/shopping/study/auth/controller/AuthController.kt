package com.shopping.study.auth.controller

import com.shopping.study.auth.dto.UsersDto
import com.shopping.study.auth.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthController(
    private val authService: AuthService
) {
    val loginPage: String = "login.html"


    @GetMapping("/login")
    fun getLoginPage(model: Model): String {
        return loginPage
    }

    @PostMapping("/login")
    fun requestLogin(@RequestParam(name = "") user_id: String, user_pw: String) {
        authService.authenticate(user_id, user_pw)
    }
}