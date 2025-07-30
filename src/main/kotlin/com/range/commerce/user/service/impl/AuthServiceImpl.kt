package com.range.commerce.user.service.impl

import com.range.commerce.common.util.JwtUtil
import com.range.commerce.user.domain.model.User
import com.range.commerce.user.domain.repository.UserRepository
import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest
import com.range.commerce.user.exception.AlreadyRegisteredException
import com.range.commerce.user.exception.AuthenticationException
import com.range.commerce.user.service.AuthService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
): AuthService {
    override fun login(loginRequest: LoginRequest) : String{
        val user =loginHelper(loginRequest)
        return jwtUtil.generateToken(user,user.role)

    }

    override fun register(registerRequest: RegisterRequest) :String{
        val exits=  userRepository.existsByEmailOrUsername(registerRequest.email, registerRequest.password)
        if(exits){
            throw AlreadyRegisteredException("User already registered")
        }

        val user = userRepository.save(registerRequest.toUser( passwordEncoder
            .encode(registerRequest.password)))

        return jwtUtil.generateToken(user,user.role)

    }

    fun loginHelper(loginRequest: LoginRequest) : User {
        return if (isEmail(loginRequest.identifier)){
            emailLogin(loginRequest)
        }else {
            usernameLogin(loginRequest)
        }
    }

    private fun isEmail(input: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(input)
    }

    private fun checkPass(password: String,userpass: String){
        if(passwordEncoder.matches(password,userpass) ){
                throw AuthenticationException("Invalid username or password")
            }
    }
    private fun emailLogin(loginRequest: LoginRequest): User {
        val user = userRepository.findByEmail(loginRequest.identifier)
            ?: throw AuthenticationException("Invalid username or password")
        checkPass(user.password, loginRequest.password)
        return user
    }
    private fun usernameLogin(loginRequest: LoginRequest): User {
        val user = userRepository.findByUsername(loginRequest.identifier)
            ?: throw AuthenticationException("Invalid username or password")
        checkPass(user.password, loginRequest.password)
        return user
    }

}