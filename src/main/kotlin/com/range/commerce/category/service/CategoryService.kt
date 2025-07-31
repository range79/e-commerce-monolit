package com.range.commerce.category.service

import com.range.commerce.category.domain.enttity.Category
import org.springframework.data.domain.Page

interface CategoryService {
    fun create(name:String):Category
    fun findAll(size: Int,page: Int): Page<Category>
    fun delete(id:Long)
    fun update(id:Long,name:String):Category
}