package com.range.commerce.category.service

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.dto.CategoryRequest
import org.springframework.data.domain.Page

interface CategoryService {
    fun create(request: CategoryRequest)
    fun findAll(size: Int,page: Int): Page<Category>
    fun delete(id:Long)
    fun update(id:Long,name:String):Category
}