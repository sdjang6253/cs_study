package com.example.demo.config

import com.example.demo.entity.User
import com.example.demo.entity.Todo
import com.example.demo.repository.UserRepository
import com.example.demo.repository.TodoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val todoRepository: TodoRepository
) : CommandLineRunner {

    private val passwordEncoder = BCryptPasswordEncoder()

    override fun run(vararg args: String?) {
        // 테스트 사용자가 없으면 생성
        if (!userRepository.existsByEmail("test@example.com")) {
            val testUser = User(
                email = "test@example.com",
                password = passwordEncoder.encode("test123"),
                nickname = "테스트유저"
            )
            val savedUser = userRepository.save(testUser)

            // 테스트 Todo 생성
            val todos = listOf(
                Todo(title = "첫 번째 할일", description = "이것은 테스트용 할일입니다.", isDone = false, user = savedUser),
                Todo(title = "완료된 할일", description = "이미 완료된 할일입니다.", isDone = true, user = savedUser),
                Todo(title = "중요한 미팅 준비", description = "내일 있을 중요한 미팅을 위한 자료 준비", isDone = false, user = savedUser)
            )
            
            todoRepository.saveAll(todos)
            
            println("테스트 데이터가 생성되었습니다:")
            println("이메일: test@example.com")
            println("비밀번호: test123")
        }
    }
}
