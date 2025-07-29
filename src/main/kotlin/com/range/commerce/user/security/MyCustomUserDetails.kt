package com.range.commerce.user.security

import com.range.commerce.user.domain.model.User
import org.springframework.security.core.GrantedAuthority

import org.springframework.security.core.userdetails.UserDetails

class MyCustomUserDetails(
    private val user: User
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return user.role
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getUsername(): String? {
        return user.username
    }
}