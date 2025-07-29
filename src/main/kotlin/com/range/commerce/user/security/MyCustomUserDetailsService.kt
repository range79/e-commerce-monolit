package com.range.commerce.user.security

import com.range.commerce.user.domain.repository.UserRepository
import com.range.commerce.user.exception.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyCustomUserDetailsService(
    private val userRepository: UserRepository,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        if (username == null) {
            throw AuthenticationException("Username cannot be null or empty")
        }
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("Username not found")
    return MyCustomUserDetails(user)

    }
}