package com.shopping.study.product.dto

import java.io.Serializable

data class ProductDto(
    val id: Long,
    val productName: String,
    val price: Int,
    val stock: Int,
    val detail: String,
    val categoryId: Long
) : Serializable
