package com.range.commerce.common.security

import com.range.commerce.common.util.JWTUtil
import com.range.commerce.user.security.MyCustomUserDetails
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
@Component
class JwtFilter (
    private val  myCustomUserDetails: MyCustomUserDetails,
    private val jWTUtil: JWTUtil
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val  header = request.getHeader("Authorization")
        if (header != null && header.isNotEmpty() && header.startsWith("Bearer ")) {
           val token = header.substring(7)
            jWTUtil.validateToken(token,myCustomUserDetails)
        }
    }
}