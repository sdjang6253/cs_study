package com.example.demo.repository

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class TodoRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var todoRepository: TodoRepository

    private lateinit var user1: User
    private lateinit var user2: User

    @BeforeEach
    fun setUp() {
        user1 = User().apply {
            email = "user1@example.com"
            password = "password"
            nickname = "유저1"
        }
        user2 = User().apply {
            email = "user2@example.com"
            password = "password"
            nickname = "유저2"
        }
        
        entityManager.persist(user1)
        entityManager.persist(user2)
        entityManager.flush()
    }

    @Test
    fun `사용자별 Todo 목록 조회 테스트`() {
        // given
        val todo1 = Todo().apply {
            title = "유저1의 할일1"
            description = "설명1"
            user = user1
        }
        val todo2 = Todo().apply {
            title = "유저1의 할일2"
            description = "설명2"
            user = user1
        }
        val todo3 = Todo().apply {
            title = "유저2의 할일1"
            description = "설명3"
            user = user2
        }

        entityManager.persist(todo1)
        entityManager.persist(todo2)
        entityManager.persist(todo3)
        entityManager.flush()

        // when
        val user1Todos = todoRepository.findByUser(user1)
        val user2Todos = todoRepository.findByUser(user2)

        // then
        assertEquals(2, user1Todos.size)
        assertEquals(1, user2Todos.size)
        
        assertTrue(user1Todos.all { it.user == user1 })
        assertTrue(user2Todos.all { it.user == user2 })
    }

    @Test
    fun `ID와 사용자로 Todo 찾기 테스트`() {
        // given
        val todo = Todo().apply {
            title = "테스트 할일"
            description = "설명"
            user = user1
        }
        entityManager.persist(todo)
        entityManager.flush()
        val todoId = todo.id!!

        // when
        val foundTodo = todoRepository.findByIdAndUser(todoId, user1)
        val notFoundTodo = todoRepository.findByIdAndUser(todoId, user2)

        // then
        assertNotNull(foundTodo)
        assertEquals("테스트 할일", foundTodo?.title)
        assertEquals(user1, foundTodo?.user)
        
        assertNull(notFoundTodo)
    }

    @Test
    fun `Todo 저장 테스트`() {
        // given
        val todo = Todo().apply {
            title = "새로운 할일"
            description = "새로운 설명"
            user = user1
        }

        // when
        val savedTodo = todoRepository.save(todo)

        // then
        assertNotNull(savedTodo.id)
        assertEquals("새로운 할일", savedTodo.title)
        assertEquals("새로운 설명", savedTodo.description)
        assertEquals(user1, savedTodo.user)
        assertFalse(savedTodo.isDone)
    }

    @Test
    fun `Todo 삭제 테스트`() {
        // given
        val todo = Todo().apply {
            title = "삭제할 할일"
            description = "설명"
            user = user1
        }
        entityManager.persist(todo)
        entityManager.flush()
        val todoId = todo.id!!

        // when
        todoRepository.delete(todo)
        entityManager.flush()

        // then
        val deletedTodo = todoRepository.findById(todoId)
        assertFalse(deletedTodo.isPresent)
    }

    @Test
    fun `빈 사용자의 Todo 목록 조회 테스트`() {
        // given
        val emptyUser = User().apply {
            email = "empty@example.com"
            password = "password"
            nickname = "빈유저"
        }
        entityManager.persist(emptyUser)
        entityManager.flush()

        // when
        val todos = todoRepository.findByUser(emptyUser)

        // then
        assertTrue(todos.isEmpty())
    }
}