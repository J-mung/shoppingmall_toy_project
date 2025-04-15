package com.shopping.study.auth.controller

import com.shopping.study.auth.dto.loginDto
import com.shopping.study.auth.dto.logoutDto
import com.shopping.study.auth.service.AuthService
import com.shopping.study.util.annotations.CheckLoginState
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    val loginPage: String = "login"

    /**
     * login 페이지 요청
     */
    @GetMapping("/login")
    fun getLoginPage(model: Model): String {
        return loginPage
    }

    /**
     * login 요청
     */
    @PostMapping("/login")
    @CheckLoginState
    fun handleLoginRequest(@RequestBody loginDto: loginDto, request: HttpServletRequest): ResponseEntity<Any> {
        return authService.login(loginDto, request)
    }

    @PostMapping("/logout")
    fun handleLogoutRequest(@RequestBody logoutDto: logoutDto, request: HttpServletRequest): ResponseEntity<Map<String, String>> {
        return authService.logout(logoutDto, request)
    }
}
