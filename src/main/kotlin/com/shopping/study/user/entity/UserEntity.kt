package com.shopping.study.user.entity

import jakarta.persistence.*

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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        other as UserEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
