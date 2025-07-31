package com.range.commerce.category.controller

import com.range.commerce.category.api.CategoryApi
import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.service.CategoryService
import org.apache.coyote.Response
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val categoryService: CategoryService
): CategoryApi {
    override fun findAll(
        size: Int,
        page: Int
    ): ResponseEntity<Page<Category>> {
        return ResponseEntity.ok(categoryService.findAll(size, page))
    }

    override fun deleteCategory(id: Long) {
        return categoryService.delete(id)
    }

    override fun create(name: String): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.create(name))
    }

    override fun updateCategory(id:Long,name: String): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.update(id,name))
    }
}