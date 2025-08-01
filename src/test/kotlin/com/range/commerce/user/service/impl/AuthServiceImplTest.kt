package com.range.commerce.user.service.impl

import com.range.commerce.common.util.JwtUtil
import com.range.commerce.user.domain.model.Role
import com.range.commerce.user.domain.model.User
import com.range.commerce.user.domain.repository.UserRepository
import com.range.commerce.user.dto.request.LoginRequest
import com.range.commerce.user.dto.request.RegisterRequest
import com.range.commerce.user.exception.AuthenticationException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class AuthServiceImplTest {
    @MockK
    lateinit var userRepository: UserRepository
    @MockK
    lateinit var passwordEncoder: PasswordEncoder
    @MockK
    lateinit var jwtUtil: JwtUtil
    @MockK
    lateinit var authService: AuthServiceImpl

    lateinit var user: User
    lateinit var loginRequest: LoginRequest
    lateinit var registerRequest: RegisterRequest

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        //entities
        user = User(
            id = UUID.randomUUID(),
            username = "test",
            password = "test",
            email = "test@gmail.com",
            role = Role.ROLE_USER,
        )

        loginRequest= LoginRequest("username", "password")
        registerRequest = RegisterRequest("username", "somemail@gmail.com", "password")

        authService= AuthServiceImpl(passwordEncoder, userRepository, jwtUtil)


        every { userRepository.save(user) } returns user


        //stub
        every { jwtUtil.generateToken(user, Role.ROLE_USER) } returns "token"
        every{userRepository.findByEmail(any())}.returns(user)
        every { userRepository.findByUsername(any()) } returns user
        every { passwordEncoder.matches(any(),any()) }returns true


    }

    @Test
    fun `login success with username and password`() {



        val result =authService.login(loginRequest)
        assertEquals(result,"token")
    }

    @Test
    fun `login success with email and password`() {
        val result =authService.login(loginRequest = LoginRequest("token@test.com","test"))
        assertEquals(result,"token")
    }
    @Test
    fun `should throw exception on invalid password`() {
        every { passwordEncoder.matches(any(),any()) }returns false
        assertThrows<AuthenticationException> {authService.login(loginRequest)}
    }

    @Test
    fun `should throw exception on when username not found`() {
        every{userRepository.findByUsername(any())} returns null
        assertThrows <AuthenticationException> {authService.login(loginRequest)}
    }



    @Test
    fun register() {
        every { userRepository.existsByEmailOrUsername(any(), any()) } returns false
        every{passwordEncoder.encode(any())} returns "123456"
        every { userRepository.save(any()) } returns user

        val result =authService.register(registerRequest = RegisterRequest(user.email, user.username,user.password))
        assertEquals(result,"token")
    }

}