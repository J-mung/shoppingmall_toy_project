package com.shopping.study.category.service

import com.shopping.study.category.entity.CategoryEntity
import com.shopping.study.category.repository.CategoryRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    @Cacheable("categories")
    fun getAllCategories(): List<CategoryEntity> {
        return categoryRepository.findAll()
    }
}
