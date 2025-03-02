package com.shopping.study.product.entity

import jakarta.persistence.*
import com.shopping.study.category.entity.CategoryEntity

@Entity
@Table(name = "products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryEntity,

    @Column(name = "product_name", nullable = false, length = 50)
    val productName: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val stock: Int
)
