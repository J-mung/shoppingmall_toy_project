package com.shopping.study.product.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "products")
class ProductEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = 0,

    @Column(name = "category_id")
    val categoryId: Int? = 0,

    @Column(name = "product_name", length = 50)
    val productName: String? = "",

    @Column(name = "price")
    val price: Int? = 0,

    @Column(name = "stock")
    val stock: Int? = 0,

    @Column(name = "detail", length = 128)
    val detail: String? = ""
)