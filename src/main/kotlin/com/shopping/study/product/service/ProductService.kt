package com.shopping.study.product.service

import com.shopping.study.category.service.CategoryService
import com.shopping.study.product.dto.CategoryProductsVO
import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.entity.ProductEntity
import com.shopping.study.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService
) {

    // 상품 엔티티를 DTO로 변환하는 단순 매핑 함수
    private fun ProductEntity.toDto(): ProductDto = ProductDto(
        id = this.id!!,
        productName = this.productName,
        price = this.price,
        stock = this.stock,
        detail = this.detail,
        categoryId = this.category.id!!
    )

    // 카테고리별로 상품 목록을 조회하는 메서드
    fun getAllProductsOrganizedByCategory(): List<CategoryProductsVO> {
        val categories = categoryService.getAllCategories()
        return categories.map { category ->
            val products = productRepository.findByCategoryId(category.id!!)
            CategoryProductsVO(
                categoryId = category.id!!,
                categoryName = category.name,
                products = products.map { it.toDto() }
            )
        }
    }

    fun updateProductDetail(productId: Long, newDetail: String) {
        val product = productRepository.findById(productId)
            .orElseThrow { IllegalArgumentException("해당 상품을 찾을 수 없습니다. id=$productId") }

        // data class ProductEntity를 사용 중이라면, copy로 새 인스턴스 생성
        // 만약 일반 클래스라면 setter 또는 별도의 update 메서드가 필요
        val updatedProduct = product.copy(detail = newDetail)

        productRepository.save(updatedProduct)
    }
}
