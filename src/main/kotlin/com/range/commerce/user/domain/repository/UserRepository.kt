package com.range.commerce.user.domain.repository

import com.range.commerce.user.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
    fun findByUsername(username: String): Optional<User>

    fun existsByEmailOrUsername(email: String, username: String): Boolean
}