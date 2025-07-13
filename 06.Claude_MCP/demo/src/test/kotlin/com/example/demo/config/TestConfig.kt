package com.example.demo.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@TestConfiguration
class TestConfig {

    /**
     * 테스트용 BCrypt 인코더 - 빠른 테스트를 위해 strength를 낮춤
     */
    @Bean
    @Primary
    fun testPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(4) // 기본값 10보다 낮게 설정하여 테스트 속도 향상
    }
}
