package com.shopping.study.user.controller

import com.shopping.study.user.service.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api")
class UserController(
    private val userService: UserService,
    private val session: HttpSession
) {

    // 로그인 페이지 제공 (GET)
    @GetMapping("/loginpage")
    fun loginPage(): String {
        return "loginpage"
    }

    // 로그인 처리 (POST)
    @PostMapping("/login")
    fun login(@RequestParam userId: String, @RequestParam passwd: String): String {
        return if (userService.authenticate(userId, passwd)) {
            session.setAttribute("userId", userId)
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
