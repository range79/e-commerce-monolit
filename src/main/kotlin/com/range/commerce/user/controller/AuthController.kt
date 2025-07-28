package com.range.commerce.user.controller

import com.range.commerce.user.api.AuthApi
import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest
import com.range.commerce.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
): AuthApi {
    override fun login(loginRequest: LoginRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.login(loginRequest))
    }

    override fun register(registerRequest: RegisterRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.register(registerRequest))
    }
}