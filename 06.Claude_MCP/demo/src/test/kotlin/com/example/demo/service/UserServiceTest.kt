package com.example.demo.service

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignupRequest
import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService(userRepository)
    }

    @Test
    fun `회원가입 성공 테스트`() {
        // given
        val signupRequest = SignupRequest(
            email = "test@example.com",
            password = "password123",
            confirmPassword = "password123",
            nickname = "테스트유저"
        )
        val savedUser = User(
            userId = 1L,
            email = signupRequest.email,
            password = "encodedPassword", // BCrypt로 인코딩된 가정
            nickname = signupRequest.nickname
        )

        `when`(userRepository.existsByEmail(signupRequest.email)).thenReturn(false)
        `when`(userRepository.save(any(User::class.java))).thenReturn(savedUser)

        // when
        val result = userService.signup(signupRequest)

        // then
        assertTrue(result.isSuccess)
        val user = result.getOrNull()
        assertNotNull(user)
        assertEquals(signupRequest.email, user?.email)
        assertEquals(signupRequest.nickname, user?.nickname)
        
        verify(userRepository).existsByEmail(signupRequest.email)
        verify(userRepository).save(any(User::class.java))
    }

    @Test
    fun `중복 이메일 회원가입 실패 테스트`() {
        // given
        val signupRequest = SignupRequest(
            email = "existing@example.com",
            password = "password123",
            confirmPassword = "password123",
            nickname = "테스트유저"
        )

        `when`(userRepository.existsByEmail(signupRequest.email)).thenReturn(true)

        // when
        val result = userService.signup(signupRequest)

        // then
        assertTrue(result.isFailure)
        assertEquals("이미 사용 중인 이메일입니다.", result.exceptionOrNull()?.message)
        
        verify(userRepository).existsByEmail(signupRequest.email)
        verify(userRepository, never()).save(any())
    }

    @Test
    fun `비밀번호 불일치 회원가입 실패 테스트`() {
        // given
        val signupRequest = SignupRequest(
            email = "test@example.com",
            password = "password123",
            confirmPassword = "differentPassword",
            nickname = "테스트유저"
        )

        `when`(userRepository.existsByEmail(signupRequest.email)).thenReturn(false)

        // when
        val result = userService.signup(signupRequest)

        // then
        assertTrue(result.isFailure)
        assertEquals("비밀번호가 일치하지 않습니다.", result.exceptionOrNull()?.message)
        
        verify(userRepository).existsByEmail(signupRequest.email)
        verify(userRepository, never()).save(any())
    }

    @Test
    fun `빈 필드 회원가입 실패 테스트`() {
        // given
        val signupRequest = SignupRequest(
            email = "",
            password = "password123",
            confirmPassword = "password123",
            nickname = "테스트유저"
        )

        `when`(userRepository.existsByEmail(signupRequest.email)).thenReturn(false)

        // when
        val result = userService.signup(signupRequest)

        // then
        assertTrue(result.isFailure)
        assertEquals("모든 필드를 입력해주세요.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `로그인 성공 테스트`() {
        // given
        val loginRequest = LoginRequest(
            email = "test@example.com",
            password = "password123"
        )
        // 실제 BCrypt로 인코딩된 패스워드 사용
        val passwordEncoder = org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode("password123")
        
        val user = User(
            userId = 1L,
            email = loginRequest.email,
            password = encodedPassword,
            nickname = "테스트유저"
        )

        `when`(userRepository.findByEmail(loginRequest.email)).thenReturn(user)

        // when
        val result = userService.login(loginRequest)

        // then
        assertTrue(result.isSuccess)
        val userSession = result.getOrNull()
        assertNotNull(userSession)
        assertEquals(user.userId, userSession?.id)
        assertEquals(user.email, userSession?.email)
        assertEquals(user.nickname, userSession?.nickname)
        
        verify(userRepository).findByEmail(loginRequest.email)
    }

    @Test
    fun `존재하지 않는 사용자 로그인 실패 테스트`() {
        // given
        val loginRequest = LoginRequest(
            email = "nonexistent@example.com",
            password = "password123"
        )

        `when`(userRepository.findByEmail(loginRequest.email)).thenReturn(null)

        // when
        val result = userService.login(loginRequest)

        // then
        assertTrue(result.isFailure)
        assertEquals("이메일 또는 비밀번호가 잘못되었습니다.", result.exceptionOrNull()?.message)
        
        verify(userRepository).findByEmail(loginRequest.email)
    }

    @Test
    fun `잘못된 비밀번호 로그인 실패 테스트`() {
        // given
        val loginRequest = LoginRequest(
            email = "test@example.com",
            password = "wrongPassword"
        )
        // 다른 패스워드로 인코딩
        val passwordEncoder = org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
        val encodedPassword = passwordEncoder.encode("correctPassword")
        
        val user = User(
            userId = 1L,
            email = loginRequest.email,
            password = encodedPassword,
            nickname = "테스트유저"
        )

        `when`(userRepository.findByEmail(loginRequest.email)).thenReturn(user)

        // when
        val result = userService.login(loginRequest)

        // then
        assertTrue(result.isFailure)
        assertEquals("이메일 또는 비밀번호가 잘못되었습니다.", result.exceptionOrNull()?.message)
        
        verify(userRepository).findByEmail(loginRequest.email)
    }

    @Test
    fun `ID로 사용자 찾기 테스트`() {
        // given
        val userId = 1L
        val user = User(
            userId = userId,
            email = "test@example.com",
            password = "password",
            nickname = "테스트유저"
        )

        `when`(userRepository.findById(userId)).thenReturn(Optional.of(user))

        // when
        val foundUser = userService.findById(userId)

        // then
        assertNotNull(foundUser)
        assertEquals(userId, foundUser?.userId)
        assertEquals("test@example.com", foundUser?.email)
        
        verify(userRepository).findById(userId)
    }

    @Test
    fun `존재하지 않는 ID로 사용자 찾기 테스트`() {
        // given
        val userId = 999L

        `when`(userRepository.findById(userId)).thenReturn(Optional.empty())

        // when
        val foundUser = userService.findById(userId)

        // then
        assertNull(foundUser)
        
        verify(userRepository).findById(userId)
    }
}
