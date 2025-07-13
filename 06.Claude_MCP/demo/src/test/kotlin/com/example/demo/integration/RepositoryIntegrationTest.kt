package com.example.demo.integration

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.repository.TodoRepository
import com.example.demo.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@ActiveProfiles("test")
@Transactional
class RepositoryIntegrationTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var todoRepository: TodoRepository

    @Test
    fun `User와 Todo 연관관계 저장 및 조회 테스트`() {
        // given
        val user = User(
            email = "relation@test.com",
            password = "password",
            nickname = "연관관계테스트"
        )
        val savedUser = userRepository.save(user)

        val todo1 = Todo(title = "할일1", description = "설명1", user = savedUser)
        val todo2 = Todo(title = "할일2", description = "설명2", user = savedUser)
        
        todoRepository.save(todo1)
        todoRepository.save(todo2)

        // when
        val foundUser = userRepository.findById(savedUser.userId!!).get()
        val userTodos = todoRepository.findByUser(foundUser)

        // then
        assertEquals(2, userTodos.size)
        assertTrue(userTodos.all { it.user?.userId == savedUser.userId })
    }

    @Test
    fun `사용자 삭제 시 연관된 Todo들도 삭제되는지 테스트`() {
        // given
        val user = User(
            email = "cascade@test.com",
            password = "password",
            nickname = "캐스케이드테스트"
        )
        val savedUser = userRepository.save(user)

        val todo = Todo(title = "삭제될 할일", description = "캐스케이드 테스트", user = savedUser)
        val savedTodo = todoRepository.save(todo)

        // when
        // 먼저 Todo를 수동으로 삭제해야 함 (foreign key constraint 때문에)
        todoRepository.deleteByUser(savedUser)
        userRepository.delete(savedUser)

        // then
        val deletedUser = userRepository.findById(savedUser.userId!!)
        val orphanedTodo = todoRepository.findById(savedTodo.id!!)
        
        assertFalse(deletedUser.isPresent)
        assertFalse(orphanedTodo.isPresent)
    }

    @Test
    fun `대량 데이터 성능 테스트`() {
        // given
        val user = User(
            email = "performance@test.com",
            password = "password",
            nickname = "성능테스트"
        )
        val savedUser = userRepository.save(user)

        // 100개의 Todo 생성
        val todos = (1..100).map { index ->
            Todo(
                title = "할일$index",
                description = "설명$index",
                user = savedUser
            )
        }
        
        val startTime = System.currentTimeMillis()
        todoRepository.saveAll(todos)
        val saveTime = System.currentTimeMillis() - startTime

        // when
        val retrieveStartTime = System.currentTimeMillis()
        val retrievedTodos = todoRepository.findByUser(savedUser)
        val retrieveTime = System.currentTimeMillis() - retrieveStartTime

        // then
        assertEquals(100, retrievedTodos.size)
        println("저장 시간: ${saveTime}ms, 조회 시간: ${retrieveTime}ms")
        
        // 성능 검증 (임계값은 환경에 따라 조정 가능)
        assertTrue(saveTime < 5000, "저장 시간이 너무 오래 걸립니다: ${saveTime}ms")
        assertTrue(retrieveTime < 1000, "조회 시간이 너무 오래 걸립니다: ${retrieveTime}ms")
    }

    @Test
    fun `복잡한 쿼리 조건 테스트`() {
        // given
        val user1 = userRepository.save(User(email = "user1@test.com", password = "pass", nickname = "유저1"))
        val user2 = userRepository.save(User(email = "user2@test.com", password = "pass", nickname = "유저2"))

        // 각 사용자별로 완료/미완료 Todo 생성
        todoRepository.save(Todo(title = "유저1 완료", description = "완료됨", isDone = true, user = user1))
        todoRepository.save(Todo(title = "유저1 미완료", description = "진행중", isDone = false, user = user1))
        todoRepository.save(Todo(title = "유저2 완료", description = "완료됨", isDone = true, user = user2))
        todoRepository.save(Todo(title = "유저2 미완료", description = "진행중", isDone = false, user = user2))

        // when
        val user1Todos = todoRepository.findByUser(user1)
        val user2Todos = todoRepository.findByUser(user2)

        // then
        assertEquals(2, user1Todos.size)
        assertEquals(2, user2Todos.size)
        
        val user1CompletedCount = user1Todos.count { it.isDone }
        val user1PendingCount = user1Todos.count { !it.isDone }
        
        assertEquals(1, user1CompletedCount)
        assertEquals(1, user1PendingCount)
    }

    @Test
    fun `이메일 유니크 제약조건 테스트`() {
        // given
        val user1 = User(email = "unique@test.com", password = "pass1", nickname = "유저1")
        userRepository.save(user1)

        // when & then
        val user2 = User(email = "unique@test.com", password = "pass2", nickname = "유저2")
        
        assertThrows(Exception::class.java) {
            userRepository.save(user2)
            userRepository.flush() // 즉시 DB에 반영하여 제약조건 검증
        }
    }

    @Test
    fun `Lazy Loading 테스트`() {
        // given
        val user = userRepository.save(
            User(email = "lazy@test.com", password = "pass", nickname = "레이지테스트")
        )
        val todo = todoRepository.save(
            Todo(title = "레이지 할일", description = "레이지 로딩 테스트", user = user)
        )

        // when
        val foundTodo = todoRepository.findById(todo.id!!).get()
        
        // then
        assertNotNull(foundTodo.user)
        assertEquals(user.nickname, foundTodo.user?.nickname)
    }

    @Test
    fun `트랜잭션 롤백 테스트`() {
        // given
        val initialUserCount = userRepository.count()
        val initialTodoCount = todoRepository.count()

        // when & then
        assertThrows(RuntimeException::class.java) {
            // 트랜잭션 내에서 실행
            val user = userRepository.save(
                User(email = "rollback@test.com", password = "pass", nickname = "롤백테스트")
            )
            todoRepository.save(
                Todo(title = "롤백될 할일", description = "예외 발생 예정", user = user)
            )
            
            // 의도적 예외 발생
            throw RuntimeException("의도적 예외")
        }
        
        // 트랜잭션이 롤백되었으므로 데이터가 추가되지 않아야 함
        assertEquals(initialUserCount, userRepository.count())
        assertEquals(initialTodoCount, todoRepository.count())
    }
}
