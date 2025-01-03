# observability 를 테스트할 프로젝트 
- 환경 
```
Open JDK 17
Gradle 8.11
Spring Boot 3.4.1
```
From Spring initializer (https://start.spring.io/)

---

- 사용 방법
```
#Docker Hub 에 public 으로 이미지가 올라가 있기에 받기
docker pull sdjang/demo-spring-observability:1

#백그라운드로 docker 실행 및 포트 열어주기 
docker run -d -p 8080:8080 sdjang/demo-spring-observability:1 

#수행이후 테스트 - 숫자가 랜덤하게 뽑혀 나오면 정상 작동 확인 
curl http://localhost:8080/rolldice
``` 

## 해당 이미지를 생성한 이유는, 앞으로 있을 Observability 에 사용하기 위함,

## 필요시 버전 추가 api 구현 예정