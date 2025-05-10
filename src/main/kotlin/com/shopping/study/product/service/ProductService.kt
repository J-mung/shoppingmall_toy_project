package com.shopping.study.product.service

import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.mapper.ProductMapper
import com.shopping.study.product.mapper.ProductMapperRegistry
import com.shopping.study.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService (
    val productRepository: ProductRepository,
    val productMapper: ProductMapper,
    val productMapperRegistry: ProductMapperRegistry
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

    /**
     * 카테고리 목록 내에서 검색
     */
    fun getCategoryProductList(categoryId: Int, productName: String): List<ProductDto> {
        val productEntity = productRepository.findAllByCategoryId(categoryId)
        val searchResult = mutableListOf<ProductDto>()
            productEntity.map { product ->
            if (product.productName.contains(productName)) {
                searchResult.add(productMapper.toDto(product))
            }
        }

        return searchResult
    }

    /**
     * 카테고리에 해당하는 상품 리스트 조회
     */
    fun getCategoryItemDetailList(categoryId: Int): List<Any> {
        val productEntity = productRepository.findAllByCategoryId(categoryId)
        val productDtoList = productEntity.map { it ->
            productMapper.toDto(it)
        }
        return productDtoList.map { it->
            val mapper = productMapperRegistry.mapperRegistry[it.categoryId]
                ?: throw IllegalArgumentException("지원하지 않는 카테고리 ID ${it.categoryId}")
            mapper(it)
        }

    }
}