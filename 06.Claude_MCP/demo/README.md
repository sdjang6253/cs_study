# Claude Desktop + Intellij MCP 를 사용하여 개발,
- 인프런 인강 url : https://inf.run/fUJwc
--- 

# 📝 Todo REST API

Kotlin + Spring Boot 3.x 기반의 간단하고 효율적인 Todo 관리 REST API입니다.

## 🚀 기술 스택

- **Language**: Kotlin
- **Framework**: Spring Boot 3.5.3
- **Database**: H2 Database (In-Memory)
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Gradle
- **Test**: JUnit 5, Mockito

## 📋 주요 기능

- ✅ **CRUD 작업**: Todo 생성, 조회, 수정, 삭제
- ✅ **RESTful API**: HTTP 메서드와 상태 코드 준수
- ✅ **데이터 검증**: 입력값 유효성 검사
- ✅ **예외 처리**: 적절한 에러 응답
- ✅ **테스트 데이터**: 애플리케이션 시작 시 샘플 Todo 자동 생성
- ✅ **완전한 테스트**: 단위/통합 테스트 포함

## 🏗️ 프로젝트 구조

```
src/
├── main/kotlin/com/example/demo/
│   ├── DemoApplication.kt              # 메인 애플리케이션
│   ├── config/
│   │   └── DataLoader.kt               # 테스트 데이터 로더
│   ├── entity/
│   │   └── Todo.kt                     # Todo 엔티티
│   ├── repository/
│   │   └── TodoRepository.kt           # 데이터 액세스 레이어
│   ├── dto/
│   │   └── TodoDto.kt                  # 데이터 전송 객체
│   ├── service/
│   │   └── TodoService.kt              # 비즈니스 로직
│   └── controller/
│       └── TodoController.kt           # REST API 컨트롤러
├── main/resources/
│   └── application.properties          # 애플리케이션 설정
└── test/kotlin/com/example/demo/       # 테스트 코드
    ├── service/TodoServiceTest.kt
    ├── controller/TodoControllerTest.kt
    ├── repository/TodoRepositoryTest.kt
    └── integration/TodoIntegrationTest.kt
```

## 🔧 설치 및 실행

### 1. 프로젝트 클론
```bash
git clone <repository-url>
cd demo
```

### 2. 애플리케이션 실행
```bash
./gradlew bootRun
```

또는 IntelliJ IDEA에서 `DemoApplication.kt`의 `main` 함수를 실행하세요.

### 3. 애플리케이션 접속
- **API 서버**: http://localhost:9090
- **H2 Database Console**: http://localhost:9090/h2-console

## ⚠️ **중요: JSON 필드명 이슈와 해결**

### **문제 상황**
Kotlin의 Boolean 프로퍼티 `isDone`이 Jackson 직렬화 시 `done`으로 변환되는 문제가 있었습니다.

```json
// 기대하는 형태
{"id": 1, "title": "제목", "isDone": true}

// 실제 출력되던 형태  
{"id": 1, "title": "제목", "done": true}
```

### **원인**
- Jackson의 기본 동작: Boolean 프로퍼티에서 `is` 접두사 자동 제거
- Kotlin의 `isDone` → JSON의 `done`으로 변환

### **해결 방법**
1. **Jackson 어노테이션 사용**: `@JsonProperty("isDone")` 추가
2. **전역 Jackson 설정**: Custom annotation introspector 구현
3. **application.properties 설정**: Jackson property naming strategy 설정

이제 모든 API 응답에서 `isDone` 필드명이 올바르게 유지됩니다.

## 📖 API 문서

### Base URL
```
http://localhost:9090/api/todos
```

### 엔드포인트

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/todos` | 전체 Todo 조회 |
| GET | `/api/todos/{id}` | 특정 Todo 조회 |
| POST | `/api/todos` | 새 Todo 생성 |
| PUT | `/api/todos/{id}` | Todo 수정 |
| DELETE | `/api/todos/{id}` | Todo 삭제 |

### 📝 초기 테스트 데이터

애플리케이션 시작 시 다음 테스트 데이터가 자동으로 생성됩니다:

```json
{
  "id": 1,
  "title": "아침일찍 일어나기",
  "description": "주중은 오전 7시, 주말은 오전 9시",
  "isDone": true
}
```

### API 사용 예시

#### 1. 전체 Todo 조회
```bash
curl -X GET http://localhost:9090/api/todos
```

**응답 예시:**
```json
[
  {
    "id": 1,
    "title": "아침일찍 일어나기",
    "description": "주중은 오전 7시, 주말은 오전 9시",
    "isDone": true
  }
]
```

#### 2. 특정 Todo 조회
```bash
curl -X GET http://localhost:9090/api/todos/1
```

#### 3. Todo 생성
```bash
curl -X POST http://localhost:9090/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "title": "운동하기",
    "description": "주 3회 이상 헬스장 가기"
  }'
```

**응답 예시:**
```json
{
  "id": 2,
  "title": "운동하기",
  "description": "주 3회 이상 헬스장 가기",
  "isDone": false
}
```

#### 4. Todo 수정
```bash
curl -X PUT http://localhost:9090/api/todos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "아침일찍 일어나기",
    "description": "주중은 오전 6시 30분, 주말은 오전 8시",
    "isDone": false
  }'
```

#### 5. Todo 삭제
```bash
curl -X DELETE http://localhost:9090/api/todos/1
```

**응답**: `204 No Content`

## 🗄️ 데이터베이스

### H2 Database 접속 정보
- **URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (비어있음)
- **Console**: http://localhost:9090/h2-console

### Todo 테이블 구조
```sql
CREATE TABLE todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_done BOOLEAN NOT NULL DEFAULT FALSE
);
```

## 🧪 테스트

이 프로젝트는 포괄적인 테스트 스위트를 포함하고 있어 안정적이고 신뢰할 수 있는 코드를 제공합니다.

### 📋 테스트 구조

```
src/test/kotlin/com/example/demo/
├── entity/                     # 엔티티 테스트
│   ├── UserTest.kt
│   └── TodoTest.kt
├── repository/                 # 데이터 액세스 계층 테스트
│   ├── UserRepositoryTest.kt
│   └── TodoRepositoryTest.kt
├── service/                    # 비즈니스 로직 테스트
│   ├── UserServiceTest.kt
│   └── TodoServiceTest.kt
├── controller/                 # 컨트롤러 계층 테스트
│   ├── UserControllerTest.kt
│   └── TodoControllerTest.kt
├── integration/                # 통합 테스트
│   ├── TodoIntegrationTest.kt
│   └── RepositoryIntegrationTest.kt
├── security/                   # 보안 테스트
│   └── SecurityTest.kt
├── util/                       # 테스트 유틸리티
│   └── TestDataFactory.kt
└── config/
    └── TestConfig.kt
```

### 🚀 테스트 실행

#### 전체 테스트 실행
```bash
# Windows
./run-tests.bat

# Linux/Mac
chmod +x run-tests.sh
./run-tests.sh

# 또는 Gradle 직접 사용
./gradlew test
```

#### 개별 테스트 실행
```bash
# 단위 테스트만 실행
./gradlew test --tests "*Test"

# 통합 테스트만 실행  
./gradlew test --tests "*IntegrationTest"

# 보안 테스트만 실행
./gradlew test --tests "*SecurityTest"

# 특정 클래스 테스트
./gradlew test --tests "TodoServiceTest"
```

### 📊 테스트 종류와 커버리지

#### 1. **단위 테스트 (Unit Tests)**
- **Entity 테스트**: 도메인 모델 검증
- **Service 테스트**: 비즈니스 로직 검증 (Mockito 사용)
- **Repository 테스트**: 데이터 액세스 로직 검증 (@DataJpaTest)
- **Controller 테스트**: REST API 엔드포인트 검증 (@WebMvcTest)

#### 2. **통합 테스트 (Integration Tests)**
- **전체 워크플로우 테스트**: 회원가입부터 Todo 관리까지
- **사용자별 데이터 격리**: 멀티유저 환경 검증
- **권한 체크**: 접근 제어 검증
- **트랜잭션 무결성**: 데이터 일관성 검증

#### 3. **보안 테스트 (Security Tests)**
- **비밀번호 암호화**: BCrypt 강도 검증
- **SQL Injection 방지**: JPA를 통한 자동 방지 확인
- **XSS 방지**: 입력값 검증 및 이스케이프
- **동시성 공격**: 멀티스레드 환경 테스트

### 🎯 주요 테스트 시나리오

#### **회원 관리 테스트**
```kotlin
// 회원가입 성공 케이스
@Test
fun `회원가입 성공 테스트`()

// 중복 이메일 방지
@Test  
fun `중복 이메일 회원가입 실패 테스트`()

// 로그인 검증
@Test
fun `로그인 성공 테스트`()
```

#### **Todo 관리 테스트**
```kotlin
// CRUD 전체 생명주기
@Test
fun `Todo CRUD 전체 생명주기 테스트`()

// 사용자별 데이터 격리
@Test
fun `사용자별 Todo 격리 테스트`()

// 권한 없는 접근 차단
@Test
fun `권한 없는 Todo 접근 차단 테스트`()
```

#### **성능 테스트**
```kotlin
// 대량 데이터 처리
@Test
fun `대량 데이터 성능 테스트`()

// 동시성 처리
@Test
fun `동시성 공격 방지 테스트`()
```

### 🔧 테스트 설정

#### **테스트 프로파일 (application-test.properties)**
```properties
# 테스트용 H2 데이터베이스
spring.datasource.url=jdbc:h2:mem:testdb

# 테스트용 세션 설정
server.servlet.session.timeout=10m

# 빠른 테스트를 위한 로깅 최적화
spring.jpa.show-sql=false
logging.level.com.example.demo=DEBUG
```

#### **테스트 데이터 팩토리**
```kotlin
// 테스트용 데이터 생성 유틸리티
object TestDataFactory {
    fun createUser(email: String = "test@example.com"): User
    fun createTodo(title: String = "테스트 할일"): Todo
    fun createSignupRequest(): SignupRequest
}
```

### 📈 테스트 리포트

테스트 실행 후 다음 위치에서 상세한 리포트를 확인할 수 있습니다:

```
build/reports/tests/test/index.html    # 테스트 결과 리포트
build/reports/jacoco/test/html/        # 코드 커버리지 리포트 (설정 시)
```

### ✅ 테스트 검증 항목

#### **기능 검증**
- ✅ 회원가입/로그인/로그아웃
- ✅ Todo CRUD 기능
- ✅ 세션 기반 인증
- ✅ 사용자별 데이터 격리

#### **보안 검증**  
- ✅ 비밀번호 BCrypt 암호화
- ✅ SQL Injection 방지
- ✅ XSS 공격 방지
- ✅ 세션 고정 공격 방지

#### **성능 검증**
- ✅ 대량 데이터 처리 (100개 Todo)
- ✅ 동시성 환경에서 데이터 무결성
- ✅ 응답 시간 임계값 검증

#### **예외 처리 검증**
- ✅ 잘못된 입력값 처리
- ✅ 권한 없는 접근 차단
- ✅ 존재하지 않는 리소스 접근

### 🎓 테스트 베스트 프랙티스

이 프로젝트에서 적용된 테스트 베스트 프랙티스:

1. **AAA 패턴**: Arrange-Act-Assert 구조
2. **Mock 사용**: 외부 의존성 격리
3. **테스트 데이터 팩토리**: 일관된 테스트 데이터 생성
4. **프로파일 분리**: 테스트 환경 독립성
5. **통합 테스트**: 실제 시나리오 검증

### 🔍 테스트 디버깅

테스트 실패 시 디버깅 방법:

```bash
# 상세한 로그와 함께 테스트 실행
./gradlew test --info --stacktrace

# 특정 테스트만 디버그 모드로 실행
./gradlew test --tests "UserServiceTest" --debug-jvm
```

## 🔧 설정

### application.properties
```properties
# 서버 설정
server.port=9090

# H2 데이터베이스 설정
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# JPA 설정
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 콘솔 활성화
spring.h2.console.enabled=true
```

## 📦 의존성

주요 라이브러리:
- `spring-boot-starter-web`: 웹 애플리케이션 구성
- `spring-boot-starter-data-jpa`: JPA 및 Hibernate 설정
- `spring-boot-starter-validation`: 데이터 검증
- `h2`: 인메모리 데이터베이스
- `kotlin-reflect`: Kotlin 리플렉션

## 🔄 개발 워크플로우

1. **애플리케이션 시작** → 자동으로 테스트 데이터 생성
2. **API 테스트** → HTTP 클라이언트나 curl 사용
3. **데이터 확인** → H2 Console에서 SQL 쿼리 실행
4. **코드 수정** → IntelliJ IDEA에서 자동 리로드
5. **테스트 실행** → IDE에서 테스트 실행

## 🎯 주요 기능 상세

### 1. 자동 데이터 초기화
- 애플리케이션 시작 시 `DataLoader`가 샘플 Todo 데이터 생성
- 개발 및 테스트 용도로 즉시 사용 가능

### 2. 데이터 검증
- 제목 필수값 검증 (`@NotBlank`)
- 잘못된 요청 시 적절한 에러 메시지 반환

### 3. 예외 처리
- 존재하지 않는 Todo 조회 시 `404 Not Found`
- 잘못된 요청 데이터 시 `400 Bad Request`
- 성공적인 삭제 시 `204 No Content`

### 4. 트랜잭션 관리
- Service 레이어에서 `@Transactional` 적용
- 데이터 무결성 보장

## 🚀 배포

### JAR 파일 생성
```bash
./gradlew bootJar
```

### 실행
```bash
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

## 📊 API 응답 형식

### 성공 응답
- **200 OK**: 조회, 수정 성공
- **201 Created**: 생성 성공
- **204 No Content**: 삭제 성공

### 에러 응답
- **400 Bad Request**: 잘못된 요청 데이터
- **404 Not Found**: 리소스를 찾을 수 없음

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 있습니다.

---

**🎉 Happy Coding!** 

궁금한 점이 있으시면 언제든지 문의해주세요.

### 💡 참고사항

- 애플리케이션 재시작 시 인메모리 DB 특성상 데이터가 초기화됩니다
- 테스트 데이터는 애플리케이션 시작 시마다 자동으로 생성됩니다
- H2 Console을 통해 실시간으로 데이터베이스 상태를 확인할 수 있습니다
