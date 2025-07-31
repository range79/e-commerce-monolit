
package com.range.commerce.category.api

import com.range.commerce.category.domain.enttity.Category
import com.range.commerce.user.domain.model.Role
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("\${app.base-url}/category")
interface CategoryApi {
    @GetMapping("/all")
    fun findAll(@RequestParam(defaultValue = "10") size: Int,
                @RequestParam(defaultValue = "10") page: Int
    ): ResponseEntity<Page<Category>>
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    fun deleteCategory(@PathVariable id: Long)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{name}")
    fun create(@PathVariable name: String):ResponseEntity<Category>
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/update/{id}/{name}")
    fun updateCategory(@PathVariable id: Long ,@PathVariable name: String): ResponseEntity<Category>
}