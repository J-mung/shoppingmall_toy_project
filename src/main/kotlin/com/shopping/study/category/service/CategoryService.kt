package com.shopping.study.category.service

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.mapper.CategoryMapper
import com.shopping.study.category.repository.CategoryRepository
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService (
    val categoryRepository: CategoryRepository,
    val categoryMapper: CategoryMapper
) {
    /**
     * 카테고리 리스트 조회
     */
    @Transactional(readOnly = true)
    @Cacheable(value = ["category"], key = "'all'")
    fun getCategoryList(): List<CategoryDto> {
        val categoryEntity = categoryRepository.findAll()
        return categoryEntity.map {
            categoryMapper.toDto(it)
        }
    }

    /**
     * 카테고리 변경
     */
    @CachePut(value = ["category"], key = "'all'")
    fun updateCategory(categoryDto: CategoryDto): List<CategoryDto> {
        val categoryEntity = categoryRepository.findById(categoryDto.categoryId)
            .orElseThrow {
                NoSuchElementException("Entity with id $id not found")
            }

        categoryDto.categoryName.ifEmpty {
            throw IllegalArgumentException("There is no category name")
        }

        categoryEntity.categoryName = categoryDto.categoryName
        categoryRepository.save(categoryEntity)

        // categoryDto 리스트를 새로 덮어야 하기 때문에 전체 조회
        val categoryEntityList = categoryRepository.findAll()
        return categoryEntityList.map { categoryMapper.toDto(it) }
    }
}