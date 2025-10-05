# 📍 Map of memory Backend

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
