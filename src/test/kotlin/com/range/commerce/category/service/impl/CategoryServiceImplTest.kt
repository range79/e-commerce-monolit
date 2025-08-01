package com.range.commerce.category.service.impl

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.domain.repository.CategoryRepository
import com.range.commerce.category.exception.CategoryNotFoundException
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.verify
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.verify
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.Optional

class CategoryServiceImplTest {
    @MockK
    private lateinit var categoryRepository: CategoryRepository
    private lateinit var service: CategoryServiceImpl
    private lateinit var name : String
    private lateinit var category : Category
    private lateinit var pageable: Pageable
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        service = CategoryServiceImpl(categoryRepository)
        name = "test"
        category = Category(1, "test")
        pageable = PageRequest.of(0, 10)
        every { categoryRepository.save(any()) } returns category
    }

    @Test
    fun create() {

        val result =  service.create(name)
        assertEquals(result, category)
    }

    @Test
    fun findAll() {
        val categories = listOf(
            Category(1, "Electronics"),
            Category(2, "Books")
        )
        val page = PageImpl(categories, pageable, categories.size.toLong())

        every { categoryRepository.findAll(pageable) } returns page

        val result = service.findAll(10, 0)

        assertEquals(2, result.content.size)
        assertEquals("Electronics", result.content[0].category_name)
        verify { service.findAll(10,0) }
    }


    @Test
    fun delete() {


        every { categoryRepository.findById(1) } returns Optional.of(category)
        every { categoryRepository.delete(category) } just Runs

        service.delete(1)

        verify(exactly = 1) { categoryRepository.findById(1) }
        verify(exactly = 1) { categoryRepository.delete(category) }
    }
    @Test
    fun `should throw exception when try to delete a category which does not exist`() {
        every { categoryRepository.findById(1) } returns Optional.empty()

        assertThrows<CategoryNotFoundException> {
            service.delete(1)
        }
    }


    @Test
    fun update() {

        val testCategory =Category(1, "testResponse")
        every {categoryRepository.findById(any())}returns Optional.of(category)
        every { categoryRepository.save(any()) } returns testCategory
        val result = service.update(1, "testResponse")
        assertEquals(result, category)

    }
    @Test
    fun `should throw exception when try to update a category which does not exist`() {
        every { categoryRepository.findById(1) } returns Optional.empty()
        assertThrows<CategoryNotFoundException> {
            service.update(1, "testResponse")
        }

    }
    }