package com.range.commerce.category.service.impl

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.domain.repository.CategoryRepository
import com.range.commerce.category.exception.CategoryNotFoundException
import com.range.commerce.category.service.CategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository,
): CategoryService {



    override fun create(name: String): Category {
        val category = Category(
            null,
            name,
        )
        return categoryRepository.save(category)
    }

    override fun findAll(
        size: Int,
        page: Int
    ): Page<Category> {
        val page: Pageable = PageRequest.of(page, size)
        return categoryRepository.findAll(page)
    }

    override fun delete(id: Long) {
        return categoryRepository.deleteById(id)
    }

    override fun update(id: Long, name: String): Category {
        val category = categoryRepository.findById(id).orElseThrow{
            CategoryNotFoundException("Category $id")
        }
        category.category_name=name
        return categoryRepository.save(category)

    }
}