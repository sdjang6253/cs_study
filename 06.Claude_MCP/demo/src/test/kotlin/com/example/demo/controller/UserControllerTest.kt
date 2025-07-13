package com.example.demo.controller

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignupRequest
import com.example.demo.dto.UserSession
import com.example.demo.entity.User
import com.example.demo.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(UserController::class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `회원가입 페이지 조회 테스트`() {
        mockMvc.perform(get("/signup"))
            .andExpect(status().isOk)
            .andExpect(view().name("auth/signup"))
            .andExpect(model().attributeExists("signupRequest"))
    }

    @Test
    fun `회원가입 성공 테스트`() {
        // given
        val user = User(userId = 1L, email = "test@example.com", password = "encoded", nickname = "테스트유저")
        `when`(userService.signup(any(SignupRequest::class.java))).thenReturn(Result.success(user))

        // when & then
        mockMvc.perform(
            post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "test@example.com")
                .param("password", "password123")
                .param("confirmPassword", "password123")
                .param("nickname", "테스트유저")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))
            .andExpect(flash().attribute("message", "회원가입이 완료되었습니다. 로그인해주세요."))

        verify(userService).signup(any(SignupRequest::class.java))
    }

    @Test
    fun `회원가입 실패 테스트`() {
        // given
        `when`(userService.signup(any(SignupRequest::class.java)))
            .thenReturn(Result.failure(RuntimeException("이미 사용 중인 이메일입니다.")))

        // when & then
        mockMvc.perform(
            post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "existing@example.com")
                .param("password", "password123")
                .param("confirmPassword", "password123")
                .param("nickname", "테스트유저")
        )
            .andExpect(status().isOk)
            .andExpect(view().name("auth/signup"))
            .andExpect(model().attributeExists("error"))
            .andExpect(model().attribute("error", "이미 사용 중인 이메일입니다."))

        verify(userService).signup(any(SignupRequest::class.java))
    }

    @Test
    fun `로그인 페이지 조회 테스트`() {
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk)
            .andExpect(view().name("auth/login"))
            .andExpect(model().attributeExists("loginRequest"))
    }

    @Test
    fun `로그인 성공 테스트`() {
        // given
        val userSession = UserSession(id = 1L, email = "test@example.com", nickname = "테스트유저")
        `when`(userService.login(any(LoginRequest::class.java))).thenReturn(Result.success(userSession))

        // when & then
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "test@example.com")
                .param("password", "password123")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/todos"))
            .andExpect(flash().attribute("message", "테스트유저님, 환영합니다!"))

        verify(userService).login(any(LoginRequest::class.java))
    }

    @Test
    fun `로그인 실패 테스트`() {
        // given
        `when`(userService.login(any(LoginRequest::class.java)))
            .thenReturn(Result.failure(RuntimeException("이메일 또는 비밀번호가 잘못되었습니다.")))

        // when & then
        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "test@example.com")
                .param("password", "wrongpassword")
        )
            .andExpect(status().isOk)
            .andExpect(view().name("auth/login"))
            .andExpect(model().attributeExists("error"))
            .andExpect(model().attribute("error", "이메일 또는 비밀번호가 잘못되었습니다."))

        verify(userService).login(any(LoginRequest::class.java))
    }

    @Test
    fun `로그아웃 테스트`() {
        mockMvc.perform(post("/logout"))
            .andExpect(status().is3xxRedirection)
            .andExpect(redirectedUrl("/login"))
    }
}
