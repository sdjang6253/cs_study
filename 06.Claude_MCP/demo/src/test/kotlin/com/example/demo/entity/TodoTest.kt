package com.example.demo.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TodoTest {

    @Test
    fun `Todo 엔티티 생성 테스트`() {
        // given
        val title = "테스트 할일"
        val description = "테스트 설명"

        // when
        val todo = Todo().apply {
            this.title = title
            this.description = description
        }

        // then
        assertEquals(title, todo.title)
        assertEquals(description, todo.description)
        assertFalse(todo.isDone)
        assertNull(todo.id)
        assertNull(todo.user)
    }

    @Test
    fun `Todo와 User 연관관계 테스트`() {
        // given
        val user = User().apply {
            email = "test@example.com"
            password = "password"
            nickname = "테스트유저"
        }
        val todo = Todo().apply {
            title = "테스트 할일"
            description = "테스트 설명"
        }

        // when
        todo.user = user

        // then
        assertEquals(user, todo.user)
    }

    @Test
    fun `Todo 완료 상태 변경 테스트`() {
        // given
        val todo = Todo().apply {
            title = "테스트 할일"
            description = "테스트 설명"
        }

        // when & then
        assertFalse(todo.isDone)
        
        todo.isDone = true
        assertTrue(todo.isDone)
        
        todo.isDone = false
        assertFalse(todo.isDone)
    }
}