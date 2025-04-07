package com.shopping.study.product.controller

import com.shopping.study.product.dto.UpdateProductDetailRequestDto
import com.shopping.study.product.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductRestController(
    private val productService: ProductService
) {
    // 부분 업데이트(PATCH)로 detail만 수정
    @PatchMapping("/{id}/detail")
    fun updateProductDetail(
        @PathVariable id: Long,
        @RequestBody request: UpdateProductDetailRequestDto
    ): ResponseEntity<Any> {
        productService.updateProductDetail(id, request.detail)
        return ResponseEntity.ok().build()
    }
}
