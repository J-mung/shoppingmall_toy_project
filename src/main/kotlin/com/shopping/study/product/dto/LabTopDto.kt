package com.shopping.study.product.dto

import java.io.Serializable

data class LabTopDto(
    override var id: Int,
    override var categoryId: Int,
    override var productName: String,
    override var price: Int,
    override var stock: Int?,
    var color: String = "",
    var ram: Int = 0,
): ProductInterface, Serializable
