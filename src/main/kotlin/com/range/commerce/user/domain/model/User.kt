package com.range.commerce.user.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: UUID,
    private val email: String,
    private val username: String,
    private val password: String,
    private val role: Role
)
