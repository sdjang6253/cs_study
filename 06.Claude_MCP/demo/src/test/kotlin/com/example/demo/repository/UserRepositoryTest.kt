package com.example.demo.repository

import com.example.demo.entity.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `이메일로 사용자 찾기 테스트`() {
        // given
        val email = "test@example.com"
        val user = User(
            email = email,
            password = "password",
            nickname = "테스트유저"
        )
        entityManager.persist(user)
        entityManager.flush()

        // when
        val foundUser = userRepository.findByEmail(email)

        // then
        assertNotNull(foundUser)
        assertEquals(email, foundUser?.email)
        assertEquals("테스트유저", foundUser?.nickname)
    }

    @Test
    fun `존재하지 않는 이메일로 사용자 찾기 테스트`() {
        // given
        val email = "nonexistent@example.com"

        // when
        val foundUser = userRepository.findByEmail(email)

        // then
        assertNull(foundUser)
    }

    @Test
    fun `이메일 존재 여부 확인 테스트`() {
        // given
        val email = "test@example.com"
        val user = User(
            email = email,
            password = "password",
            nickname = "테스트유저"
        )
        entityManager.persist(user)
        entityManager.flush()

        // when & then
        assertTrue(userRepository.existsByEmail(email))
        assertFalse(userRepository.existsByEmail("nonexistent@example.com"))
    }

    @Test
    fun `사용자 저장 테스트`() {
        // given
        val user = User(
            email = "new@example.com",
            password = "password",
            nickname = "새유저"
        )

        // when
        val savedUser = userRepository.save(user)

        // then
        assertNotNull(savedUser.userId)
        assertEquals("new@example.com", savedUser.email)
        assertEquals("새유저", savedUser.nickname)
    }

    @Test
    fun `중복 이메일 저장 시 예외 발생 테스트`() {
        // given
        val email = "duplicate@example.com"
        val user1 = User(email = email, password = "password1", nickname = "유저1")
        val user2 = User(email = email, password = "password2", nickname = "유저2")

        entityManager.persist(user1)
        entityManager.flush()

        // when & then
        assertThrows(Exception::class.java) {
            entityManager.persist(user2)
            entityManager.flush()
        }
    }
}
