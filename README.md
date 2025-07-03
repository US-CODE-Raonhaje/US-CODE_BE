# 📍 Map of memory Backend

Spring Boot 기반의 백엔드 프로젝트로, 사용자 인증, 데이터베이스 연동, Swagger API 문서화, JWT 인증 등을 포함합니다.

> 이 프로젝트는 OAuth2(Google) 로그인 및 JWT 기반 인증 구조를 적용한 RESTful API 서버입니다.

---

## 🛠️ Tech Stack

| 분류            | 기술 스택                                                                                            |
|----------------|----------------------------------------------------------------------------------------------------|
| **Framework**  | Spring Boot 3.x, Spring MVC, Spring Validation                                                    |
| **Security**   | Spring Security, OAuth2 Client, JWT                                                               |
| **ORM / DB**   | Spring Data JPA, QueryDSL (v5, Jakarta), MySQL                                                    |
| **Build Tool** | Gradle                                                                                            |
| **Docs**       | SpringDoc OpenAPI 3 (Swagger UI v3 기반)                                                           |
| **Testing**    | JUnit 5, Spring Security Test, Spring Boot Test                                                   |
| **Annotation** | Lombok, Jakarta Annotations (for APT)                                                             |
| **Dev Tools**  | IntelliJ IDEA, Git, GitHub Actions                                                                |

---

## 📦 Dependency Breakdown

### 🔹 Spring Boot 기반
- `spring-boot-starter-web`: RESTful API 개발용 Spring MVC 핵심 모듈
- `spring-boot-starter-validation`: Bean Validation (`@Valid`, `@NotBlank` 등)

### 🔐 보안 / 인증
- `spring-boot-starter-security`: 보안 설정 및 인증 처리  
- `spring-boot-starter-oauth2-client`: Google 등 OAuth2 연동  
- `jjwt`: JWT 기반 인증 토큰 생성 및 검증

### 🧮 데이터 접근
- `spring-boot-starter-data-jpa`: Spring Data JPA 기반 ORM
- `querydsl-jpa`: 타입 안전한 동적 쿼리 생성 (APT 기반)

### 🗃️ Database
- `mysql-connector-j`: MySQL 연동용 JDBC 드라이버
- `h2`: 개발/테스트용 인메모리 DB (주석 처리됨)

### 🧬 문서화
- `springdoc-openapi-starter-webmvc-ui`: Swagger UI 제공
- `springdoc-openapi-starter-webmvc-api`: OpenAPI 3.0 스펙 문서화

### 🧪 테스트
- `spring-boot-starter-test`: 단위 테스트 기반
- `spring-security-test`: 인증/인가 테스트 지원
- `junit-platform-launcher`: JUnit 실행 환경 구성

---

## 🗂️ 프로젝트 구조

```bash
📁 src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com.raonhaje.memorymap
 ┃ ┃   ┣ 📂member
 ┃ ┃   ┃  ┣ 📂domain        # 엔티티 클래스
 ┃ ┃   ┃  ┣ 📂dto           # 요청/응답 DTO
 ┃ ┃   ┃  ┣ 📂repository    # JPA/QueryDSL Repository
 ┃ ┃   ┃  ┣ 📂application   # 비즈니스 로직
 ┃ ┃   ┃  ┗ 📂ui            # REST API 엔드포인트
 ┃ ┃   ┣ 📂auth             # OAuth2 관련 설정 및 사용자 처리
 ┃ ┃   ┗ 📂common           # 공통 모듈 (예: 예외 처리, 유틸, 응답 포맷, 전역 설정 등)
 ┃ ┗ 📂resources
 ┃     ┣ application.properties  # 환경설정
 ┃     ┗ static/templates
 ┗ 📂test
```
---

## 🧱 아키텍처 개요

### CI/CD 구조도
<img width="1496" alt="Map-of-memory-CICD 파이프라인" src="https://github.com/user-attachments/assets/a018188c-940b-419b-af80-affe2171645c" />

---
