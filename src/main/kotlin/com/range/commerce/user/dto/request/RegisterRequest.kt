package com.range.commerce.user.dto.request

import com.range.commerce.user.domain.model.Role
import com.range.commerce.user.domain.model.User

data class RegisterRequest (
    val email: String,
    val username: String,
    val password: String,
){
    fun toUser(): User {
        return User(
            id=null,
            email = email,
            username = username,
            password=password,
            role=Role.ROLE_USER
        )
    }
}