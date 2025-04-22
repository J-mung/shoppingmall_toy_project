package com.shopping.study.product.controller

import com.shopping.study.product.ProductSearchDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/product")
class ProductRestController {
    @PostMapping("/search")
    fun searchProductList(productSearchDto: ProductSearchDto) {
        print("call rest api")
    }
}