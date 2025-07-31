package com.range.commerce.category.domain.repository

import com.range.commerce.category.domain.enttity.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    override fun findAll(pagable: Pageable): Page<Category>
}