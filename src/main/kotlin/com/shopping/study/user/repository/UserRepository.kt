package com.shopping.study.user.repository

import com.shopping.study.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByUserId(userId: String): UserEntity?
}
