package com.shopping.study.category.service

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.mapper.CategoryMapper
import com.shopping.study.category.repository.CategoryRepository
import com.shopping.study.infra.cache.GeneralCache
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service

@Service
class CategoryService (
    val categoryRepository: CategoryRepository,
    val categoryMapper: CategoryMapper
) {
    /**
     * 카테고리 리스트 조회
     */
//    @Transactional(readOnly = true)
    fun getCategoryList(): List<CategoryDto> = GeneralCache.cache("CategoryList") {
        val categoryEntity = categoryRepository.findAll()
        return@cache categoryEntity.map {
            categoryMapper.toDto(it)
        }
    }

    /**
     * 카테고리 변경
     */
    fun updateCategory(categoryDto: CategoryDto): Boolean = GeneralCache.evict("CategoryList") {
        val categoryEntity = categoryRepository.findById(categoryDto.categoryId)
            .orElseThrow {
                NoSuchElementException("Entity with id $id not found")
            }

        categoryDto.categoryName.ifEmpty {
            throw IllegalArgumentException("There is no category name")
        }

        categoryEntity.categoryName = categoryDto.categoryName
        categoryRepository.save(categoryEntity)

        return@evict true
    }

    /**
     * 카테고리 1건 조회
     */
    fun getOneCategory(categoryId: Int): CategoryDto = GeneralCache.cache("CategoryRead", "categoryId:${categoryId}") {
        val categoryEntity = categoryRepository.findById(categoryId).orElseThrow { NoSuchElementException("Entity with id $id not found") }
        return@cache categoryMapper.toDto(categoryEntity)
    }

    /**
     * 카테고리 1건 변경
     */
    fun updateOneCategory(categoryDto: CategoryDto): CategoryDto = GeneralCache.put("CategoryRead", "categoryId:${categoryDto.categoryId}") {
        val categoryEntity = categoryRepository.findById(categoryDto.categoryId)
            .orElseThrow {
                NoSuchElementException("Entity with id $id not found")
            }

        categoryEntity.categoryName = categoryDto.categoryName
        categoryRepository.save(categoryEntity)

        val updatedEntity = categoryRepository.findById(categoryDto.categoryId)
            .orElseThrow {
                NoSuchElementException("Entity with id $id not found")
            }

        return@put categoryMapper.toDto(updatedEntity)
    }
}