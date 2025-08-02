
package com.range.commerce.category.api

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.category.dto.CategoryRequest
import com.range.commerce.user.domain.model.Role
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("\${app.base-url}/category")
interface CategoryApi {
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(@RequestParam(defaultValue = "10") size: Int,
                @RequestParam(defaultValue = "0") page: Int
    ): Page<Category>


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    fun deleteCategory(@PathVariable id: Long)

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: CategoryRequest)

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}/{name}")
    fun updateCategory(@PathVariable id: Long ,@PathVariable name: String): Category
}