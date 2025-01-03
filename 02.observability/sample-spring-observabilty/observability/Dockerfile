# 1단계: 빌드 이미지를 설정 (Gradle 빌드)
FROM gradle:8.11.1-jdk17 as builder

WORKDIR /app

# 필요한 파일들 복사
COPY . .

# Gradle을 통해 빌드 (JAR 파일 생성)
RUN ./gradlew clean build -x test

# 2단계: 실행 이미지를 설정
FROM openjdk:17-alpine

WORKDIR /app

# 빌드된 JAR 파일을 빌드 이미지에서 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 8080 포트를 노출
EXPOSE 8080

# JAR 파일을 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
