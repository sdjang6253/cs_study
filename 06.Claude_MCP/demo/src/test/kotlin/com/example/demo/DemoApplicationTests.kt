package com.example.demo

import com.example.demo.repository.TodoRepository
import com.example.demo.repository.UserRepository
import com.example.demo.service.TodoService
import com.example.demo.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertNotNull

@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var todoRepository: TodoRepository

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var todoService: TodoService

    @Test
    fun contextLoads() {
        // 스프링 컨텍스트가 정상적으로 로드되는지 확인
    }

    @Test
    fun `모든 필수 빈들이 정상적으로 주입되는지 테스트`() {
        assertNotNull(userRepository)
        assertNotNull(todoRepository)
        assertNotNull(userService)
        assertNotNull(todoService)
    }

    @Test
    fun `데이터베이스 연결 테스트`() {
        // given & when
        val userCount = userRepository.count()
        val todoCount = todoRepository.count()

        // then - 예외가 발생하지 않으면 DB 연결 성공
        assertNotNull(userCount)
        assertNotNull(todoCount)
    }
}
