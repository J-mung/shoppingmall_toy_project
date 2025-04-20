package com.shopping.study.user.controller

import com.shopping.study.user.aspect.LoginRequired
import com.shopping.study.user.dto.LoginRequestDto
import com.shopping.study.user.service.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
    private val session: HttpSession
) {

    // 로그인 페이지 제공
    @GetMapping("/loginpage")
    fun loginPage(): String {
        return "loginpage"
    }

    // 로그인 처리
    @PostMapping("/login")
    @LoginRequired
    fun login(@ModelAttribute loginRequestDto: LoginRequestDto): String {
        val response = userService.authenticate(loginRequestDto)
        return if (response.user != null) {
            session.setAttribute("userId", response.user.userId)
            "redirect:/api/loginpage"
        } else {
            "redirect:/api/loginpage?error=true"
        }
    }

    @PostMapping("/logout")
    fun logout(): String {
        session.invalidate()
        return "redirect:/api/loginpage"
    }
}
