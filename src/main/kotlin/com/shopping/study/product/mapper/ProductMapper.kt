package com.shopping.study.product.mapper

import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.entity.ProductEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {
    fun toDto(productEntity: ProductEntity): ProductDto

    fun toEntity(productDto: ProductDto): ProductEntity
}