package com.shopping.study.product.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/product")
class ProductController {
    val productHome = "productHome"

    @GetMapping("/", "")
    fun getProductPage(): String {
        return productHome
    }
}