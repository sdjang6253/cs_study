package com.example.demo.util

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignupRequest
import com.example.demo.dto.UserSession
import com.example.demo.entity.Todo
import com.example.demo.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * 테스트용 데이터 생성을 위한 팩토리 클래스
 */
object TestDataFactory {
    
    private val passwordEncoder = BCryptPasswordEncoder()
    
    /**
     * 테스트용 User 생성
     */
    fun createUser(
        id: Long? = null,
        email: String = "test@example.com",
        password: String = "password123",
        nickname: String = "테스트유저"
    ): User {
        return User(
            userId = id,
            email = email,
            password = passwordEncoder.encode(password),
            nickname = nickname
        )
    }
    
    /**
     * 테스트용 Todo 생성
     */
    fun createTodo(
        id: Long? = null,
        title: String = "테스트 할일",
        description: String = "테스트 설명",
        isDone: Boolean = false,
        user: User? = null
    ): Todo {
        return Todo(
            id = id,
            title = title,
            description = description,
            isDone = isDone,
            user = user
        )
    }
    
    /**
     * 테스트용 SignupRequest 생성
     */
    fun createSignupRequest(
        email: String = "test@example.com",
        password: String = "password123",
        confirmPassword: String = "password123",
        nickname: String = "테스트유저"
    ): SignupRequest {
        return SignupRequest(
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            nickname = nickname
        )
    }
    
    /**
     * 테스트용 LoginRequest 생성
     */
    fun createLoginRequest(
        email: String = "test@example.com",
        password: String = "password123"
    ): LoginRequest {
        return LoginRequest(
            email = email,
            password = password
        )
    }
    
    /**
     * 테스트용 UserSession 생성
     */
    fun createUserSession(
        id: Long = 1L,
        email: String = "test@example.com",
        nickname: String = "테스트유저"
    ): UserSession {
        return UserSession(
            id = id,
            email = email,
            nickname = nickname
        )
    }
    
    /**
     * 비밀번호 인코딩
     */
    fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }
    
    /**
     * 비밀번호 검증
     */
    fun matchesPassword(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }
    
    /**
     * 여러 User 생성
     */
    fun createUsers(count: Int): List<User> {
        return (1..count).map { index ->
            createUser(
                email = "user$index@test.com",
                nickname = "테스트유저$index"
            )
        }
    }
    
    /**
     * 여러 Todo 생성
     */
    fun createTodos(count: Int, user: User): List<Todo> {
        return (1..count).map { index ->
            createTodo(
                title = "할일$index",
                description = "설명$index",
                isDone = index % 2 == 0, // 짝수는 완료
                user = user
            )
        }
    }
    
    /**
     * 랜덤 이메일 생성
     */
    fun generateRandomEmail(): String {
        val uuid = java.util.UUID.randomUUID().toString().substring(0, 8)
        return "test-$uuid@example.com"
    }
    
    /**
     * 랜덤 닉네임 생성
     */
    fun generateRandomNickname(): String {
        val uuid = java.util.UUID.randomUUID().toString().substring(0, 8)
        return "유저-$uuid"
    }
}
