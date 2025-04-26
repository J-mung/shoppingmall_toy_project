package com.shopping.study.product.service

import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.mapper.ProductMapper
import com.shopping.study.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (
    val productRepository: ProductRepository,
    val productMapper: ProductMapper
) {
    /**
     * 전체 상품 조회
     */
    fun getAllProductList(): List<ProductDto> {
        val productEntity = productRepository.findAll()
        val allProductList = productEntity.map{ product ->
            productMapper.toDto(product)
        }

        return allProductList
    }
}