package com.shopping.study.product.mapper

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.shopping.study.product.ProductConstants.LapTopInfo
import com.shopping.study.product.ProductConstants.ShoesDetailInfo
import com.shopping.study.product.dto.LabTopDto
import com.shopping.study.product.dto.ProductDto
import com.shopping.study.product.dto.ShoesDto
import org.springframework.stereotype.Component

@Component
class ProductMapperRegistry {
    val objectMapper = ObjectMapper()
    /** productDto를 categoryId에 해당하는 dto로 mapping 하며,
     *  그 과정에서 detail json data를 parsing.
     */
    val mapperRegistry: Map<Int, (ProductDto) -> Any> = mapOf(
        // 신발 dto
        1 to { entity ->
            val root: JsonNode = getJsonNode(entity.detail)
            ShoesDto(
                id = entity.id,
                categoryId = entity.categoryId,
                productName = entity.productName,
                price = entity.price,
                stock = entity.stock,
                size = root.get(ShoesDetailInfo.SIZE.code).asInt(),
                color = root.get(ShoesDetailInfo.COLOR.code).asText()
            )
        },
        // 노트북 dto
        2 to { entity ->
            val root: JsonNode = getJsonNode(entity.detail)
            LabTopDto(
                id = entity.id,
                categoryId = entity.categoryId,
                productName = entity.productName,
                price = entity.price,
                stock = entity.stock,
                color = root.get(LapTopInfo.COLOR.code).asText(),
                ram = root.get(LapTopInfo.RAM.code).asInt()
            )
        }
    )

    private fun getJsonNode(jsonDetail: String): JsonNode {
        return objectMapper.readTree(jsonDetail)
    }
}