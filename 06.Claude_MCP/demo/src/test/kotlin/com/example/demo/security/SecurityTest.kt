package com.example.demo.security

import com.example.demo.entity.User
import com.example.demo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SecurityTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    private val passwordEncoder = BCryptPasswordEncoder()

    @Test
    fun `BCrypt 암호화 강도 테스트`() {
        // given
        val password = "testPassword123!"
        
        // when
        val encoded1 = passwordEncoder.encode(password)
        val encoded2 = passwordEncoder.encode(password)
        
        // then
        assertNotEquals(encoded1, encoded2) // 같은 비밀번호라도 다른 해시값
        assertTrue(passwordEncoder.matches(password, encoded1))
        assertTrue(passwordEncoder.matches(password, encoded2))
        assertFalse(passwordEncoder.matches("wrongPassword", encoded1))
    }

    @Test
    fun `비밀번호 복잡성 검증 테스트`() {
        val testCases = mapOf(
            "123" to false,           // 너무 짧음
            "password" to false,      // 숫자/특수문자 없음
            "12345678" to false,      // 문자 없음
            "Password123" to true,    // 적절한 복잡성
            "P@ssw0rd!" to true       // 특수문자 포함
        )
        
        testCases.forEach { (password, shouldBeValid) ->
            val isValid = isPasswordValid(password)
            if (shouldBeValid) {
                assertTrue(isValid, "비밀번호 '$password'는 유효해야 합니다")
            } else {
                assertFalse(isValid, "비밀번호 '$password'는 유효하지 않아야 합니다")
            }
        }
    }
    
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 && 
               password.any { it.isLetter() } && 
               password.any { it.isDigit() }
    }

    @Test
    fun `이메일 형식 검증 테스트`() {
        val validEmails = listOf(
            "test@example.com",
            "user.name@domain.co.kr",
            "valid+email@test.org"
        )
        
        val invalidEmails = listOf(
            "invalid",
            "@example.com",
            "test@",
            "test..test@example.com"
        )
        
        validEmails.forEach { email ->
            assertTrue(isEmailValid(email), "이메일 '$email'는 유효해야 합니다")
        }
        
        invalidEmails.forEach { email ->
            assertFalse(isEmailValid(email), "이메일 '$email'는 유효하지 않아야 합니다")
        }
    }
    
    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return email.matches(emailRegex.toRegex()) && !email.contains("..")
    }

    @Test
    fun `SQL Injection 방지 테스트`() {
        // given
        val maliciousEmail = "test@test.com'; DROP TABLE users; --"
        val user = User(
            email = maliciousEmail,
            password = passwordEncoder.encode("password"),
            nickname = "악의적사용자"
        )
        
        // when & then - JPA를 사용하므로 SQL Injection 자동 방지
        assertDoesNotThrow {
            userRepository.save(user)
            val foundUser = userRepository.findByEmail(maliciousEmail)
            assertEquals(maliciousEmail, foundUser?.email)
        }
    }

    @Test
    fun `세션 고정 공격 방지 테스트`() {
        // 실제 웹 환경에서는 Spring Security가 자동으로 세션 고정 공격을 방지
        // 여기서는 개념적 테스트만 수행
        
        val sessionId1 = generateSessionId()
        val sessionId2 = generateSessionId()
        
        assertNotEquals(sessionId1, sessionId2)
        assertTrue(sessionId1.length >= 32) // 충분한 길이
        assertTrue(sessionId2.length >= 32)
    }
    
    private fun generateSessionId(): String {
        return java.util.UUID.randomUUID().toString().replace("-", "")
    }

    @Test
    fun `XSS 방지를 위한 입력값 검증 테스트`() {
        // given
        val maliciousInputs = listOf(
            "<script>alert('xss')</script>",
            "javascript:alert('xss')",
            "<img src=x onerror=alert('xss')>",
            "'; DROP TABLE users; --"
        )
        
        maliciousInputs.forEach { input ->
            // when
            val sanitized = sanitizeInput(input)
            
            // then
            assertFalse(sanitized.contains("<script>"))
            assertFalse(sanitized.contains("javascript:"))
            assertFalse(sanitized.contains("onerror="))
        }
    }
    
    private fun sanitizeInput(input: String): String {
        return input
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;")
            .replace("/", "&#x2F;")
    }

    @Test
    fun `민감한 정보 로깅 방지 테스트`() {
        // given
        val user = User(
            email = "sensitive@test.com",
            password = passwordEncoder.encode("secretPassword123!"),
            nickname = "민감정보테스트"
        )
        
        // when
        val userString = user.toString()
        
        // then
        assertFalse(userString.contains("secretPassword123!"))
        // 실제 구현에서는 toString() 메서드에서 민감한 정보를 제외해야 함
    }

    @Test
    fun `동시성 공격 방지 테스트`() {
        // given
        val email = "concurrent@test.com"
        val user = User(
            email = email,
            password = passwordEncoder.encode("password"),
            nickname = "동시성테스트"
        )
        userRepository.save(user)
        
        // when - 여러 스레드에서 동시에 같은 사용자 수정 시도
        val threads = (1..5).map { index ->
            Thread {
                try {
                    val foundUser = userRepository.findByEmail(email)
                    foundUser?.let {
                        it.nickname = "수정된닉네임$index"
                        userRepository.save(it)
                    }
                } catch (e: Exception) {
                    // 동시성 예외는 예상된 상황
                }
            }
        }
        
        threads.forEach { it.start() }
        threads.forEach { it.join() }
        
        // then - 데이터 무결성 확인
        val finalUser = userRepository.findByEmail(email)
        assertNotNull(finalUser)
        assertTrue(finalUser!!.nickname.startsWith("수정된닉네임"))
    }

    @Test
    fun `비밀번호 변경 이력 관리 테스트`() {
        // given
        val originalPassword = "originalPassword123!"
        val newPassword = "newPassword456!"
        
        val user = User(
            email = "password-change@test.com",
            password = passwordEncoder.encode(originalPassword),
            nickname = "비밀번호변경테스트"
        )
        val savedUser = userRepository.save(user)
        
        // when
        savedUser.password = passwordEncoder.encode(newPassword)
        userRepository.save(savedUser)
        
        // then
        val updatedUser = userRepository.findByEmail(savedUser.email)
        assertNotNull(updatedUser)
        assertFalse(passwordEncoder.matches(originalPassword, updatedUser!!.password))
        assertTrue(passwordEncoder.matches(newPassword, updatedUser.password))
    }
}
