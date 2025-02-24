package com.shopping.study.auth.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

    @GetMapping("/great")
    fun getLoginPage(model: Model): String {
        model.addAttribute("message", "Hello, Kotlin + Spring Boot!")
        return "login.html"
    }
}