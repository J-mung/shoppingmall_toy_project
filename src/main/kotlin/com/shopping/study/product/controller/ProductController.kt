package com.shopping.study.product.controller

import com.shopping.study.product.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/list")
    fun viewProductList(model: Model): String {
        val categoryProducts = productService.getAllProductsOrganizedByCategory()
        model.addAttribute("categories", categoryProducts)
        return "productList"
    }
}
