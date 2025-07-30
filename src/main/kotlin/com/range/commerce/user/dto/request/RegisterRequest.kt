package com.range.commerce.user.dto.request

import com.range.commerce.user.domain.model.Role
import com.range.commerce.user.domain.model.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min

data class RegisterRequest (
    @Email(message = "Please enter a valid email address")
    val email: String,
    val username: String,
    @Min(8, message = "Password must be at least 8 characters long")
    val password: String,
){
    fun toUser(encodedPass: String): User {
        return User(
            id=null,
            email = email,
            username = username,
            password=encodedPass,
            role= Role.ROLE_USER
        )
    }
}