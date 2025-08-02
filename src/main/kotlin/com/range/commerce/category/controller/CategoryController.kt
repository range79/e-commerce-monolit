package com.range.commerce.category.controller

import com.range.commerce.category.api.CategoryApi
import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.dto.CategoryRequest
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
    ): Page<Category> {
        return categoryService.findAll(size, page)
    }

    override fun deleteCategory(id: Long) {
        return categoryService.delete(id)
    }

    override fun create(request: CategoryRequest) {
       categoryService.create(request)
    }

    override fun updateCategory(id:Long,name: String):Category {
        return categoryService.update(id,name)
    }
}