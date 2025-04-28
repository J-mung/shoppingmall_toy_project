package com.shopping.study.product.controller

import com.shopping.study.category.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/product")
class ProductController (
    val categoryService: CategoryService
) {
    val productHome = "productHome"

    @GetMapping("/", "")
    fun getProductPage(model: Model): String {
        model.addAttribute("categoryList", categoryService.getCategoryList())
        return productHome
    }
}