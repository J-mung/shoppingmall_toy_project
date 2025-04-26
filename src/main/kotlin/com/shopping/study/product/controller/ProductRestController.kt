package com.shopping.study.product.controller

import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.dto.ProductSearchDto
import com.shopping.study.product.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/product")
class ProductRestController (
    val productService: ProductService
) {
    @PostMapping("/search")
    fun searchProductList(productSearchDto: ProductSearchDto): List<ProductDto> {
        print("call rest api")
        return productService.getAllProductList()
    }
}