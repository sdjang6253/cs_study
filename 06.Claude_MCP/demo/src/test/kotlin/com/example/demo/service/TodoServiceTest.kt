package com.example.demo.service

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.repository.TodoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TodoServiceTest {

    @Mock
    private lateinit var todoRepository: TodoRepository

    @InjectMocks
    private lateinit var todoService: TodoService

    private lateinit var user: User
    private lateinit var otherUser: User

    @BeforeEach
    fun setUp() {
        user = User().apply {
            userId = 1L
            email = "test@example.com"
            password = "password"
            nickname = "테스트유저"
        }
        otherUser = User().apply {
            userId = 2L
            email = "other@example.com"
            password = "password"
            nickname = "다른유저"
        }
    }

    @Test
    fun `사용자별 모든 Todo 조회 테스트`() {
        // given
        val todo1 = Todo().apply {
            id = 1L
            title = "할일1"
            description = "설명1"
            user = this@TodoServiceTest.user
        }
        val todo2 = Todo().apply {
            id = 2L
            title = "할일2"
            description = "설명2"
            user = this@TodoServiceTest.user
        }
        val todos = listOf(todo1, todo2)

        `when`(todoRepository.findByUser(user)).thenReturn(todos)

        // when
        val result = todoService.getAllTodosByUser(user)

        // then
        assertEquals(2, result.size)
        assertEquals(todos, result)
        
        verify(todoRepository).findByUser(user)
    }

    @Test
    fun `ID와 사용자로 Todo 조회 테스트`() {
        // given
        val todoId = 1L
        val todo = Todo().apply {
            id = todoId
            title = "할일1"
            description = "설명1"
            user = this@TodoServiceTest.user
        }

        `when`(todoRepository.findByIdAndUser(todoId, user)).thenReturn(todo)

        // when
        val result = todoService.getTodoByIdAndUser(todoId, user)

        // then
        assertEquals(todo, result)
        
        verify(todoRepository).findByIdAndUser(todoId, user)
    }

    @Test
    fun `다른 사용자의 Todo 조회 시 null 반환 테스트`() {
        // given
        val todoId = 1L

        `when`(todoRepository.findByIdAndUser(todoId, otherUser)).thenReturn(null)

        // when
        val result = todoService.getTodoByIdAndUser(todoId, otherUser)

        // then
        assertNull(result)
        
        verify(todoRepository).findByIdAndUser(todoId, otherUser)
    }

    @Test
    fun `Todo 저장 테스트`() {
        // given
        val todo = Todo().apply {
            title = "새로운 할일"
            description = "새로운 설명"
        }
        val savedTodo = Todo().apply {
            id = 1L
            title = "새로운 할일"
            description = "새로운 설명"
            user = this@TodoServiceTest.user
        }

        `when`(todoRepository.save(any(Todo::class.java))).thenReturn(savedTodo)

        // when
        val result = todoService.saveTodo(todo, user)

        // then
        assertEquals(user, result.user)
        assertEquals("새로운 할일", result.title)
        assertEquals("새로운 설명", result.description)
        
        verify(todoRepository).save(any(Todo::class.java))
    }

    @Test
    fun `Todo 수정 성공 테스트`() {
        // given
        val todoId = 1L
        val existingTodo = Todo().apply {
            id = todoId
            title = "기존 할일"
            description = "기존 설명"
            user = this@TodoServiceTest.user
        }
        val updatedTodo = Todo().apply {
            title = "수정된 할일"
            description = "수정된 설명"
        }
        val savedTodo = Todo().apply {
            id = todoId
            title = "수정된 할일"
            description = "수정된 설명"
            user = this@TodoServiceTest.user
        }

        `when`(todoRepository.findByIdAndUser(todoId, user)).thenReturn(existingTodo)
        `when`(todoRepository.save(existingTodo)).thenReturn(savedTodo)

        // when
        val result = todoService.updateTodo(todoId, updatedTodo, user)

        // then
        assertEquals("수정된 할일", result.title)
        assertEquals("수정된 설명", result.description)
        assertEquals(user, result.user)
        
        verify(todoRepository).findByIdAndUser(todoId, user)
        verify(todoRepository).save(existingTodo)
    }

    @Test
    fun `권한 없는 Todo 수정 시 예외 발생 테스트`() {
        // given
        val todoId = 1L
        val updatedTodo = Todo().apply {
            title = "수정된 할일"
            description = "수정된 설명"
        }

        `when`(todoRepository.findByIdAndUser(todoId, otherUser)).thenReturn(null)

        // when & then
        val exception = assertThrows(RuntimeException::class.java) {
            todoService.updateTodo(todoId, updatedTodo, otherUser)
        }
        
        assertEquals("Todo not found or access denied", exception.message)
        
        verify(todoRepository).findByIdAndUser(todoId, otherUser)
        verify(todoRepository, never()).save(any())
    }

    @Test
    fun `Todo 완료 상태 토글 테스트`() {
        // given
        val todoId = 1L
        val todo = Todo().apply {
            id = todoId
            title = "할일"
            description = "설명"
            isDone = false
            user = this@TodoServiceTest.user
        }

        `when`(todoRepository.findByIdAndUser(todoId, user)).thenReturn(todo)
        `when`(todoRepository.save(todo)).thenReturn(todo)

        // when
        todoService.toggleTodo(todoId, user)

        // then
        assertTrue(todo.isDone)
        
        verify(todoRepository).findByIdAndUser(todoId, user)
        verify(todoRepository).save(todo)
    }

    @Test
    fun `권한 없는 Todo 토글 시 예외 발생 테스트`() {
        // given
        val todoId = 1L

        `when`(todoRepository.findByIdAndUser(todoId, otherUser)).thenReturn(null)

        // when & then
        val exception = assertThrows(RuntimeException::class.java) {
            todoService.toggleTodo(todoId, otherUser)
        }
        
        assertEquals("Todo not found or access denied", exception.message)
        
        verify(todoRepository).findByIdAndUser(todoId, otherUser)
        verify(todoRepository, never()).save(any())
    }

    @Test
    fun `Todo 삭제 성공 테스트`() {
        // given
        val todoId = 1L
        val todo = Todo().apply {
            id = todoId
            title = "할일"
            description = "설명"
            user = this@TodoServiceTest.user
        }

        `when`(todoRepository.findByIdAndUser(todoId, user)).thenReturn(todo)

        // when
        todoService.deleteTodo(todoId, user)

        // then
        verify(todoRepository).findByIdAndUser(todoId, user)
        verify(todoRepository).delete(todo)
    }

    @Test
    fun `권한 없는 Todo 삭제 시 예외 발생 테스트`() {
        // given
        val todoId = 1L

        `when`(todoRepository.findByIdAndUser(todoId, otherUser)).thenReturn(null)

        // when & then
        val exception = assertThrows(RuntimeException::class.java) {
            todoService.deleteTodo(todoId, otherUser)
        }
        
        assertEquals("Todo not found or access denied", exception.message)
        
        verify(todoRepository).findByIdAndUser(todoId, otherUser)
        verify(todoRepository, never()).delete(any())
    }

    @Test
    fun `빈 Todo 목록 조회 테스트`() {
        // given
        `when`(todoRepository.findByUser(user)).thenReturn(emptyList())

        // when
        val result = todoService.getAllTodosByUser(user)

        // then
        assertTrue(result.isEmpty())
        
        verify(todoRepository).findByUser(user)
    }
}