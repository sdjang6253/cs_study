package com.example.demo.controller

import com.example.demo.dto.UserSession
import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.service.TodoService
import com.example.demo.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(TodoController::class)
@ActiveProfiles("test")
class TodoControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var todoService: TodoService

    @MockBean
    private lateinit var userService: UserService

    private lateinit var session: MockHttpSession
    private lateinit var user: User
    private lateinit var userSession: UserSession

    @BeforeEach
    fun setUp() {
        user = User().apply {
            userId = 1L
            email = "test@example.com"
            password = "password"
            nickname = "테스트유저"
        }
        
        userSession = UserSession(
            id = 1L,
            email = "test@example.com",
            nickname = "테스트유저"
        )
        
        session = MockHttpSession()
        session.setAttribute("user", userSession)
        
        `when`(userService.findById(1L)).thenReturn(user)
    }

    @Test
    fun `로그인하지 않은 사용자 Todo 목록 조회 시 로그인 페이지로 리다이렉트`() {
        mockMvc.perform(get("/todos"))
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))
    }

    @Test
    fun `Todo 목록 조회 테스트`() {
        // given
        val todo1 = Todo().apply {
            id = 1L
            title = "할일1"
            description = "설명1"
            user = this@TodoControllerTest.user
        }
        val todo2 = Todo().apply {
            id = 2L
            title = "할일2"
            description = "설명2"
            user = this@TodoControllerTest.user
        }
        val todos = listOf(todo1, todo2)
        `when`(todoService.getAllTodosByUser(user)).thenReturn(todos)

        // when & then
        mockMvc.perform(get("/todos").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/list"))
            .andExpect(model().attributeExists("todos"))
            .andExpect(model().attributeExists("currentUser"))

        verify(todoService).getAllTodosByUser(user)
    }

    @Test
    fun `Todo 상세 조회 테스트`() {
        // given
        val todo = Todo().apply {
            id = 1L
            title = "할일1"
            description = "설명1"
            user = this@TodoControllerTest.user
        }
        `when`(todoService.getTodoByIdAndUser(1L, user)).thenReturn(todo)

        // when & then
        mockMvc.perform(get("/todos/1").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/detail"))
            .andExpect(model().attributeExists("todo"))
            .andExpect(model().attributeExists("currentUser"))

        verify(todoService).getTodoByIdAndUser(1L, user)
    }

    @Test
    fun `Todo 생성 폼 페이지 조회 테스트`() {
        mockMvc.perform(get("/todos/create").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/create"))
            .andExpect(model().attributeExists("todo"))
            .andExpect(model().attributeExists("currentUser"))
    }

    @Test
    fun `Todo 생성 테스트`() {
        // given
        val savedTodo = Todo().apply {
            id = 1L
            title = "새로운 할일"
            description = "새로운 설명"
            user = this@TodoControllerTest.user
        }
        `when`(todoService.saveTodo(any(Todo::class.java), eq(user))).thenReturn(savedTodo)

        // when & then
        mockMvc.perform(
            post("/todos/create")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "새로운 할일")
                .param("description", "새로운 설명")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))
            .andExpect(flash().attribute("message", "Todo가 성공적으로 등록되었습니다."))

        verify(todoService).saveTodo(any(Todo::class.java), eq(user))
    }

    @Test
    fun `Todo 수정 폼 페이지 조회 테스트`() {
        // given
        val todo = Todo().apply {
            id = 1L
            title = "할일1"
            description = "설명1"
            user = this@TodoControllerTest.user
        }
        `when`(todoService.getTodoByIdAndUser(1L, user)).thenReturn(todo)

        // when & then
        mockMvc.perform(get("/todos/1/edit").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/edit"))
            .andExpect(model().attributeExists("todo"))
            .andExpect(model().attributeExists("currentUser"))

        verify(todoService).getTodoByIdAndUser(1L, user)
    }

    @Test
    fun `Todo 수정 테스트`() {
        // given
        val updatedTodo = Todo().apply {
            id = 1L
            title = "수정된 할일"
            description = "수정된 설명"
            user = this@TodoControllerTest.user
        }
        `when`(todoService.updateTodo(eq(1L), any(Todo::class.java), eq(user))).thenReturn(updatedTodo)

        // when & then
        mockMvc.perform(
            post("/todos/1/edit")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "수정된 할일")
                .param("description", "수정된 설명")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))
            .andExpect(flash().attribute("message", "Todo가 성공적으로 수정되었습니다."))

        verify(todoService).updateTodo(eq(1L), any(Todo::class.java), eq(user))
    }

    @Test
    fun `Todo 완료 상태 토글 테스트`() {
        mockMvc.perform(post("/todos/1/toggle").session(session))
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))

        verify(todoService).toggleTodo(1L, user)
    }

    @Test
    fun `Todo 삭제 테스트`() {
        mockMvc.perform(post("/todos/1/delete").session(session))
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))
            .andExpect(flash().attribute("message", "Todo가 성공적으로 삭제되었습니다."))

        verify(todoService).deleteTodo(1L, user)
    }

    @Test
    fun `로그인하지 않은 사용자의 Todo 생성 시도`() {
        mockMvc.perform(
            post("/todos/create")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "새로운 할일")
                .param("description", "새로운 설명")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))

        verify(todoService, never()).saveTodo(any(), any())
    }
}