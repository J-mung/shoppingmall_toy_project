package com.shopping.study.order.entity

import jakarta.persistence.*
import com.shopping.study.user.entity.UserEntity
import com.shopping.study.product.entity.ProductEntity
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @Column(nullable = false, length = 128)
    val uuid: String,

    @Column(nullable = false)
    val quantity: Int,

    @Column(name = "order_date", nullable = false)
    val orderDate: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false, length = 10)
    val zipcode: String,

    @Column(name = "delivery_address", nullable = false, length = 128)
    val deliveryAddress: String
)
