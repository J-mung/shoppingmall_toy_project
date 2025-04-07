package com.shopping.study.product.dto

data class CategoryProductsVO(
    val categoryId: Long,
    val categoryName: String,
    val products: List<ProductDto>
)
