package com.shopping.study.category.service

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.mapper.CategoryMapper
import com.shopping.study.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService (
    val categoryRepository: CategoryRepository,
    val categoryMapper: CategoryMapper
) {
    /**
     * 카테고리 리스트 조회
     */
    fun getCategoryList(): List<CategoryDto> {
        val categoryEntity = categoryRepository.findAll()
        return categoryEntity.map {
            categoryMapper.toDto(it)
        }
    }
}