package com.shopping.study.category.controller

import com.shopping.study.category.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/category")
class CategoryController(
    val categoryService: CategoryService
) {
    val productPage = "productHome"
    @GetMapping
    fun getCategoryPage(@RequestParam categoryId: Int, model: Model): String {
//        model.addAttribute("productList", categoryService.getCategoryProductList(categoryId))
        return productPage
    }
}