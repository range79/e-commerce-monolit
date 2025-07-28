package com.range.commerce.user.controller

import com.range.commerce.user.api.AuthApi
import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest
import org.springframework.http.ResponseEntity

class AuthController: AuthApi {
    override fun login(loginRequest: LoginRequest): ResponseEntity<String> {
        TODO("Not yet implemented")
    }

    override fun register(registerRequest: RegisterRequest): ResponseEntity<String> {
        TODO("Not yet implemented")
    }
}