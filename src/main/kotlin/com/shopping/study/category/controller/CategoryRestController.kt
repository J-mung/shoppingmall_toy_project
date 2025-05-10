package com.shopping.study.category.controller

import com.shopping.study.category.dto.CategoryDto
import com.shopping.study.category.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/category")
class CategoryRestController(
    val categoryService: CategoryService
) {
    @GetMapping("/list")
    fun getCategoryList(): ResponseEntity<List<CategoryDto>> {
        return ResponseEntity.ok().body(
            categoryService.getCategoryList()
        )
    }

    @GetMapping("/{categoryId}")
    fun getOneCategory(@PathVariable categoryId: Int): ResponseEntity<CategoryDto> {
        categoryService.getOneCategory(categoryId)
        return ResponseEntity.ok().body(
            categoryService.getOneCategory(categoryId)
        )
    }

    @PostMapping("/update")
    fun updateCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(
            categoryService.updateCategory(categoryDto)
        )
    }

    @PostMapping("/single/update")
    fun updateOneCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> {
        return ResponseEntity.ok().body(
            categoryService.updateOneCategory(categoryDto)
        )
    }

}