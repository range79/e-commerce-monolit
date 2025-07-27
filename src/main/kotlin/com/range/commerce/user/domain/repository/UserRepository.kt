package com.range.commerce.user.domain.repository

import com.range.commerce.user.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
}