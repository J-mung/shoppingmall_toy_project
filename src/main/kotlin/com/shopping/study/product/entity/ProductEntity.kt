package com.shopping.study.product.entity

import com.shopping.study.category.entity.CategoryEntity
import jakarta.persistence.*

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
    val stock: Int,

    // 추가된 컬럼: detail – 자식 객체의 속성을 JSON 문자열로 저장
    @Column(name = "detail", nullable = false, length = 128)
    val detail: String
)
