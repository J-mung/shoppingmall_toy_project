package com.shopping.study.product.controller

import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.dto.ProductSearchDto
import com.shopping.study.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    /**
     * 카테고리에 해당하는 상품 리스트 조회
     */
    @GetMapping("/category/{categoryId}")
    fun getCategoryItemDetailList(@PathVariable categoryId: Int) : List<Any> {
        return productService.getCategoryItemDetailList(categoryId)
    }

    /**
     * 카테고리 내에서 상품 리스트 검색어 조회
     */
    @GetMapping("/category/{categoryId}/{productName}")
    fun getCategoryItemListBySearch(@PathVariable categoryId: Int, @PathVariable productName: String): List<ProductDto> {
        return productService.getCategoryProductList(categoryId, productName)
    }


}