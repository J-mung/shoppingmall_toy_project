package com.shopping.study.category.mapper

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.entity.CategoryEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CategoryMapper {
    fun toDto(categoryEntity: CategoryEntity): CategoryDto

    fun toEntity(categoryDto: CategoryDto): CategoryEntity
}