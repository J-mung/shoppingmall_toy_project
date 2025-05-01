package com.shopping.study.category.controller

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.service.CategoryService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/category")
class CategoryRestController(
    val categoryService: CategoryService
) {
    @PostMapping("/update")
    fun updateOneCategory(@RequestBody categoryDto: CategoryDto) {
        categoryService.updateCategory(categoryDto)
    }
}