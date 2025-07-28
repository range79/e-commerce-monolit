package com.range.commerce.user.dto.request

import com.range.commerce.user.domain.model.Role
import com.range.commerce.user.domain.model.User

data class RegisterRequest (
    val email: String,
    val username: String,
    val password: String,
){
    fun toUser(encodedPass: String): User {
        return User(
            id=null,
            email = email,
            username = username,
            password=encodedPass,
            role=Role.ROLE_USER
        )
    }
}