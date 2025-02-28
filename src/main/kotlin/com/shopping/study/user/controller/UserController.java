package com.shopping.study.user.controller

import com.shopping.study.user.entity.UserEntity
import com.shopping.study.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserEntity> {
        val user = userService.getUserById(id)
        return user.map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createUser(@RequestBody user: UserEntity): ResponseEntity<UserEntity> {
        val savedUser = userService.createUser(user)
        return ResponseEntity.ok(savedUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userService.getUserById(id).isPresent) {
            userService.deleteUser(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
