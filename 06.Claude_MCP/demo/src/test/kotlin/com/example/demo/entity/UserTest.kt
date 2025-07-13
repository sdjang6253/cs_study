package com.example.demo.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `User 엔티티 생성 테스트`() {
        // given
        val email = "test@example.com"
        val password = "encodedPassword"
        val nickname = "테스트유저"

        // when
        val user = User().apply {
            this.email = email
            this.password = password
            this.nickname = nickname
        }

        // then
        assertEquals(email, user.email)
        assertEquals(password, user.password)
        assertEquals(nickname, user.nickname)
        assertTrue(user.todos.isEmpty())
        assertNull(user.userId)
    }

    @Test
    fun `User에 Todo 추가 테스트`() {
        // given
        val user = User().apply {
            email = "test@example.com"
            password = "password"
            nickname = "테스트유저"
        }
        val todo = Todo().apply {
            title = "테스트 할일"
            description = "테스트 설명"
            this.user = user
        }

        // when
        user.todos.add(todo)

        // then
        assertEquals(1, user.todos.size)
        assertEquals(todo, user.todos[0])
        assertEquals(user, todo.user)
    }
}