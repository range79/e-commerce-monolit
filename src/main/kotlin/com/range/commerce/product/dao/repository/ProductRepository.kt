package com.range.commerce.product.dao.repository

import com.range.commerce.product.dao.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository: JpaRepository<Product, UUID> {
}