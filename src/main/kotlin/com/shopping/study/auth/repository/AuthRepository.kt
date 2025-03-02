package com.shopping.study.auth.repository

import com.shopping.study.auth.entity.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthRepository : JpaRepository<UsersEntity, Long> {
    fun findByUserId(userId: String): UsersEntity?
}