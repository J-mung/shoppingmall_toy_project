package com.shopping.study.product.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Serializable

data class ShoesDto(
    override var id: Int,
    override var categoryId: Int,
    override var productName: String,
    override var price: Int,
    override var stock: Int?,
    var size: Int = 0,
    var color: String = "",
): ProductInterface, Serializable
