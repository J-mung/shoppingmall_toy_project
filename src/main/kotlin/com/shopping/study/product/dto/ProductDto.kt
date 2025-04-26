package com.shopping.study.product.dto

data class ProductDto(
    var id: Int? = 0,
    var categoryId: Int? = 0,
    var productName: String? = "",
    var price: Int? = 0,
    var stock: Int? = 0,
    var detail: String? = ""
)
