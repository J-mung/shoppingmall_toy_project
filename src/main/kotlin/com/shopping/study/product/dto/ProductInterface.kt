package com.shopping.study.product.dto

interface ProductInterface {
    var id: Int
    var categoryId: Int
    var productName: String
    var price: Int
    var stock: Int?
}