package com.example.demo.integration

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.repository.TodoRepository
import com.example.demo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
@Transactional
class TodoIntegrationTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var todoRepository: TodoRepository

    private lateinit var mockMvc: MockMvc
    private lateinit var passwordEncoder: BCryptPasswordEncoder
    private lateinit var testUser: User

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        passwordEncoder = BCryptPasswordEncoder()
        
        // 테스트 사용자 생성
        testUser = User().apply {
            email = "integration@test.com"
            password = passwordEncoder.encode("testpassword")
            nickname = "통합테스트유저"
        }
        testUser = userRepository.save(testUser)
    }

    @Test
    fun `전체 워크플로우 테스트 - 회원가입부터 Todo 관리까지`() {
        // 1. 회원가입
        mockMvc.perform(
            post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "workflow@test.com")
                .param("password", "password123")
                .param("confirmPassword", "password123")
                .param("nickname", "워크플로우유저")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))

        // 2. 로그인
        val session = MockHttpSession()
        mockMvc.perform(
            post("/login")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "workflow@test.com")
                .param("password", "password123")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))

        // 3. Todo 생성
        mockMvc.perform(
            post("/todos/create")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "통합테스트 할일")
                .param("description", "통합테스트 설명")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))

        // 4. Todo 목록 조회
        mockMvc.perform(get("/todos").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/list"))
            .andExpect(model().attributeExists("todos"))

        // 5. 생성된 Todo 확인
        val user = userRepository.findByEmail("workflow@test.com")
        assertNotNull(user)
        val todos = todoRepository.findByUser(user!!)
        assertEquals(1, todos.size)
        assertEquals("통합테스트 할일", todos[0].title)
    }

    @Test
    fun `사용자별 Todo 격리 테스트`() {
        // given - 두 번째 사용자 생성
        val user2 = User().apply {
            email = "user2@test.com"
            password = passwordEncoder.encode("password")
            nickname = "유저2"
        }
        userRepository.save(user2)

        // 각 사용자별 Todo 생성
        val todo1 = Todo().apply {
            title = "유저1의 할일"
            description = "설명1"
            user = testUser
        }
        val todo2 = Todo().apply {
            title = "유저2의 할일"
            description = "설명2"
            user = user2
        }
        todoRepository.save(todo1)
        todoRepository.save(todo2)

        // when & then - 각 사용자는 자신의 Todo만 조회 가능
        val user1Todos = todoRepository.findByUser(testUser)
        val user2Todos = todoRepository.findByUser(user2)

        assertEquals(1, user1Todos.size)
        assertEquals(1, user2Todos.size)
        assertEquals("유저1의 할일", user1Todos[0].title)
        assertEquals("유저2의 할일", user2Todos[0].title)
    }

    @Test
    fun `권한 없는 Todo 접근 차단 테스트`() {
        // given - 다른 사용자의 Todo 생성
        val otherUser = User().apply {
            email = "other@test.com"
            password = passwordEncoder.encode("password")
            nickname = "다른유저"
        }
        userRepository.save(otherUser)
        
        val otherTodo = Todo().apply {
            title = "다른 사용자의 할일"
            description = "접근 불가"
            user = otherUser
        }
        val savedTodo = todoRepository.save(otherTodo)

        // when & then - testUser로 다른 사용자의 Todo 접근 시도
        val foundTodo = todoRepository.findByIdAndUser(savedTodo.id!!, testUser)
        assertNull(foundTodo)
    }

    @Test
    fun `로그인 세션 만료 후 접근 차단 테스트`() {
        // when & then - 세션 없이 Todo 페이지 접근
        mockMvc.perform(get("/todos"))
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))
    }

    @Test
    fun `비밀번호 암호화 확인 테스트`() {
        // given
        val rawPassword = "testpassword123"
        val user = User().apply {
            email = "encrypt@test.com"
            password = passwordEncoder.encode(rawPassword)
            nickname = "암호화테스트"
        }
        
        // when
        val savedUser = userRepository.save(user)
        
        // then
        assertNotEquals(rawPassword, savedUser.password)
        assertTrue(passwordEncoder.matches(rawPassword, savedUser.password))
        assertFalse(passwordEncoder.matches("wrongpassword", savedUser.password))
    }

    @Test
    fun `중복 이메일 등록 방지 테스트`() {
        // given - 이미 존재하는 이메일로 회원가입 시도
        mockMvc.perform(
            post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", testUser.email)
                .param("password", "password123")
                .param("confirmPassword", "password123")
                .param("nickname", "중복유저")
        )
            .andExpect(status().isOk)
            .andExpect(view().name("auth/signup"))
            .andExpect(model().attributeExists("error"))
    }

    @Test
    fun `Todo CRUD 전체 생명주기 테스트`() {
        // given - 로그인 세션 설정
        val session = MockHttpSession()
        mockMvc.perform(
            post("/login")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", testUser.email)
                .param("password", "testpassword")
        )

        // 1. CREATE - Todo 생성
        mockMvc.perform(
            post("/todos/create")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "CRUD 테스트 할일")
                .param("description", "CRUD 테스트 설명")
        )
            .andExpect(status().is3xxRedirection)

        val createdTodo = todoRepository.findByUser(testUser)[0]
        
        // 2. READ - Todo 조회
        mockMvc.perform(get("/todos/${createdTodo.id}").session(session))
            .andExpect(status().isOk)
            .andExpect(view().name("todo/detail"))

        // 3. UPDATE - Todo 수정
        mockMvc.perform(
            post("/todos/${createdTodo.id}/edit")
                .session(session)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "수정된 할일")
                .param("description", "수정된 설명")
        )
            .andExpect(status().is3xxRedirection)

        val updatedTodo = todoRepository.findById(createdTodo.id!!).get()
        assertEquals("수정된 할일", updatedTodo.title)
        assertEquals("수정된 설명", updatedTodo.description)

        // 4. TOGGLE - 완료 상태 변경
        mockMvc.perform(post("/todos/${createdTodo.id}/toggle").session(session))
            .andExpect(status().is3xxRedirection)

        val toggledTodo = todoRepository.findById(createdTodo.id!!).get()
        assertTrue(toggledTodo.isDone)

        // 5. DELETE - Todo 삭제
        mockMvc.perform(post("/todos/${createdTodo.id}/delete").session(session))
            .andExpect(status().is3xxRedirection)

        val deletedTodo = todoRepository.findById(createdTodo.id!!)
        assertFalse(deletedTodo.isPresent)
    }
}