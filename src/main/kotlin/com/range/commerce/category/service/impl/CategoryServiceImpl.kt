package com.range.commerce.category.service.impl

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.domain.repository.CategoryRepository
import com.range.commerce.category.dto.CategoryRequest
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



    override fun create(request: CategoryRequest) {
        val category = Category(
            null,
            request.name,
        )
        categoryRepository.save(category)
    }

    override fun findAll(
        size: Int,
        page: Int
    ): Page<Category> {
        val page: Pageable = PageRequest.of(page, size)
        return categoryRepository.findAll(page)
    }

    override fun delete(id: Long) {
       val category = findCategory(id)
        return categoryRepository.delete(category)
    }

    override fun update(id: Long, name: String): Category {
        val category = findCategory(id)
        category.category_name=name
        return categoryRepository.save(category)

    }
    private fun findCategory(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow{
            CategoryNotFoundException("Category $id Not Found")
        }
    }
}