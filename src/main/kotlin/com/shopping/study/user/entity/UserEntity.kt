package com.shopping.study.user.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "user_id", unique = true, nullable = false, length = 128)
    val userId: String,

    @Column(nullable = false, length = 128)
    val email: String,

    @Column(nullable = false, length = 50)
    val passwd: String,

    @Column(name = "user_name", nullable = false, length = 50)
    val userName: String
)