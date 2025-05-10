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
    var id: Int = 0,

    @Column(name = "category_id")
    var categoryId: Int = 0,

    @Column(name = "product_name", length = 50)
    var productName: String = "",

    @Column(name = "price")
    var price: Int = 0,

    @Column(name = "stock")
    var stock: Int = 0,

    @Column(name = "detail", length = 128)
    var detail: String = ""
)