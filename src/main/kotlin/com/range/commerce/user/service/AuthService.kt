package com.range.commerce.user.service

import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest

interface AuthService {
    fun login(loginRequest: LoginRequest): String
    fun register(registerRequest: RegisterRequest): String
}