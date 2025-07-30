package com.range.commerce.user.security

import com.range.commerce.user.domain.repository.UserRepository
import com.range.commerce.user.exception.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MyCustomUserDetailsService(
    private val userRepository: UserRepository,
): UserDetailsService {
    override  fun loadUserByUsername(username: String): UserDetails {
        val userId =try {
            UUID.fromString(username)
        }
        catch (e: IllegalArgumentException){
            throw AuthenticationException("Invalid id")
        }
        val user = userRepository.findById(userId).orElseThrow { AuthenticationException("User not found") }
        return MyCustomUserDetails(user)

    }
}