package com.shopping.study.auth.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "users")
data class UsersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Int = 0,

    @Column(name = "user_id", length = 127)
    val userId: String = "",

    @Column(name = "email", length = 127)
    val email: String = "",

    @Column(name = "passwd", length = 50)
    val passwd: String = "",

    @Column(name = "user_name", length = 50)
    var userName: String = "",
) : Serializable