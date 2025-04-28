package com.shopping.study.product.repository

import com.shopping.study.product.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<ProductEntity, Int> {
    fun findByProductName(productName: String): ProductEntity?
    fun findAllByCategoryId(categoryId: Int): List<ProductEntity>
}