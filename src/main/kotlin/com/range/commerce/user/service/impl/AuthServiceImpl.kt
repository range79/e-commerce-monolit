package com.range.commerce.user.service.impl

import com.range.commerce.user.domain.repository.UserRepository
import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest
import com.range.commerce.user.exception.AlreadyRegisteredException
import com.range.commerce.user.exception.AuthenticationException
import com.range.commerce.user.service.AuthService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
): AuthService {
    override fun login(loginRequest: LoginRequest) {
        if (isEmail(loginRequest.identifier)){
            emailLogin(loginRequest)
        }
        usernameLogin(loginRequest)

    }

    override fun register(registerRequest: RegisterRequest) {
        val exits=  userRepository.exitsByEmailOrUsername(registerRequest.email, registerRequest.password)
        if(exits){
            throw AlreadyRegisteredException("User already registered")
        }
        val user = userRepository.save(registerRequest.toUser())


    }



    private fun isEmail(input: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(input)
    }

    private fun checkPass(password: String,userpass: String){
        if(password != userpass){
            throw AuthenticationException("Invalid email or password")
        }
    }
    private fun emailLogin(loginRequest: LoginRequest): String {
        val user = userRepository.findByEmail(loginRequest.identifier) ?: throw AuthenticationException("Invalid email or password")
        checkPass(user.password, loginRequest.password)
        return user.username
    }
    private fun usernameLogin(loginRequest: LoginRequest): String {
        val user = userRepository.findByUsername(loginRequest.identifier)
            ?: throw AuthenticationException("Invalid username or password")
        checkPass(user.password, loginRequest.password)
        return user.username
    }

}