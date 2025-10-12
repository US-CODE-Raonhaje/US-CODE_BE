# 📍 Map of memory Backend

> 기간: 2025.06.27 ~ 06.29
> 장소: 경상북도 의성군
> 주최/후원: 메이드인피플 (후원: 의성군, Google Developers)
> 팀명: 라온하제(Raonhaje)
> 역할: 백엔드 파트 개발
> **Backend Repository:** https://github.com/US-CODE-Raonhaje/US-CODE_BE

---

## **프로젝트 개요**

- 주제: **기억을 시각적으로 저장하고 연결하는 지도 (Map-of-Memory)**
- 기술 스택: Spring Boot · JPA · Docker · GCP(GKE, Cloud SQL, Storage)
- CI/CD: GitHub Actions → Artifact Registry → GKE 자동 배포 파이프라인 구축
- 목표: 해커톤 내에서 **GCP 기반 자동 배포 환경 구축 및 서비스 개발**

---

## **주요 구현 사항**

- **GitHub Actions CI/CD 구축**
    - main 브랜치 병합 시 자동 빌드, 도커 이미지 푸시 및 GKE 배포 자동화
    - Cloud SQL 및 Storage 연동 테스트 완료
- **GCP 인프라 구성**
    - Artifact Registry 이미지 관리
    - GKE 기반 컨테이너 오케스트레이션

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

## 🧱 CI/CD 구성도.

### CI/CD 구조도
<img width="1496" alt="Map-of-memory-CICD 파이프라인" src="https://github.com/user-attachments/assets/a018188c-940b-419b-af80-affe2171645c" />

---
