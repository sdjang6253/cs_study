package com.example.demo.service

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignupRequest
import com.example.demo.dto.UserSession
import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    private val passwordEncoder = BCryptPasswordEncoder()
    
    fun signup(signupRequest: SignupRequest): Result<User> {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(signupRequest.email)) {
            return Result.failure(RuntimeException("이미 사용 중인 이메일입니다."))
        }
        
        // 패스워드 확인 체크
        if (signupRequest.password != signupRequest.confirmPassword) {
            return Result.failure(RuntimeException("비밀번호가 일치하지 않습니다."))
        }
        
        // 입력값 검증
        if (signupRequest.email.isBlank() || signupRequest.password.isBlank() || signupRequest.nickname.isBlank()) {
            return Result.failure(RuntimeException("모든 필드를 입력해주세요."))
        }
        
        val user = User(
            email = signupRequest.email.trim(),
            password = passwordEncoder.encode(signupRequest.password),
            nickname = signupRequest.nickname.trim()
        )
        
        return try {
            val savedUser = userRepository.save(user)
            Result.success(savedUser)
        } catch (e: Exception) {
            Result.failure(RuntimeException("회원가입 중 오류가 발생했습니다."))
        }
    }
    
    fun login(loginRequest: LoginRequest): Result<UserSession> {
        val user = userRepository.findByEmail(loginRequest.email)
            ?: return Result.failure(RuntimeException("이메일 또는 비밀번호가 잘못되었습니다."))
        
        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            return Result.failure(RuntimeException("이메일 또는 비밀번호가 잘못되었습니다."))
        }
        
        val userSession = UserSession(
            id = user.userId!!,
            email = user.email,
            nickname = user.nickname
        )
        
        return Result.success(userSession)
    }
    
    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }
}
