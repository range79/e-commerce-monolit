package com.range.commerce.common.util

import com.range.commerce.user.domain.model.Role
import com.range.commerce.user.domain.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
@Component
class JWTUtil {

    @Value("\${app.secret}")
    lateinit var secret: String
    @Value("\${app.duration}")
    lateinit var appDuration: Integer
    fun generateToken(user: User,role: Role): String {
        return Jwts.builder()
            .subject(user.id.toString())
            .issuedAt(Date(System.currentTimeMillis()))
            .claim("role", role)
            .expiration(Date(System.currentTimeMillis() + appDuration.toLong()))
            .signWith(key())
            .compact()
    }
    fun key(): SecretKey{
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }
    fun getUserId(token: String): UUID {
        val id =Jwts.parser().verifyWith(key())
            .build().parseClaimsJws(token).payload.subject
        return UUID.fromString(id)
    }
    fun parseToken(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(key())
            .build()
            .parseSignedClaims(token)
            .getPayload()

    }
    fun validateToken(token: String,userDetails : UserDetails): Boolean {
        val claim = parseToken(token)

        val username = claim.subject

        val expiration = claim.expiration

        val expired = expiration.before(Date())

        return username == userDetails.username && !expired


    }
}