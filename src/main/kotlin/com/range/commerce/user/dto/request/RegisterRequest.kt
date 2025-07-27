package com.range.commerce.user.dto.request

data class RegisterRequest (
    private val email: String,
    private val username: String,
    private val password: String,
)