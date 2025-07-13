package com.example.demo.config

// 이 파일은 DataInitializer.kt와 중복되므로 비활성화합니다.
// DataInitializer.kt가 데이터 초기화를 담당합니다.

/*
import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.repository.TodoRepository
import com.example.demo.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // 테스트 사용자 생성
        val testUser = User(
            email = "test@example.com",
            password = "password123",
            nickname = "테스트유저"
        )
        
        val savedUser = userRepository.save(testUser)
        
        // 테스트 데이터 생성
        val testTodo = Todo(
            title = "아침일찍 일어나기",
            description = "주중은 오전 7시, 주말은 오전 9시",
            isDone = true,
            user = savedUser  // User 설정
        )
        
        todoRepository.save(testTodo)
        
        println("✅ 테스트 User 데이터가 생성되었습니다!")
        println("📧 이메일: ${savedUser.email}")
        println("👤 닉네임: ${savedUser.nickname}")
        
        println("✅ 테스트 Todo 데이터가 생성되었습니다!")
        println("📝 제목: ${testTodo.title}")
        println("📄 설명: ${testTodo.description}")
        println("✅ 완료 상태: ${testTodo.isDone}")
        println("👤 소유자: ${testTodo.user?.nickname}")
    }
}
*/
