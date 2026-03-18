# 🚀 SelfDev Community 프로젝트 작업 일지

이 파일은 프로젝트의 진행 상황을 기록하고 Gemini CLI가 문맥을 유지하기 위한 용도입니다.

## 📝 프로젝트 개요
- **목적:** 개발자들을 위한 커뮤니티 플랫폼 개발
- **기술 스택:**
  - **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA, H2 Database (In-memory)
  - **Frontend:** React, TypeScript, Vite
  - **DB URL:** `jdbc:h2:mem:testdb` (sa / password 없음)

## ✅ 완료된 작업
- [x] 프로젝트 초기 환경 설정
- [x] H2 데이터베이스 연결 설정 확인 (`application.properties`)
- [x] 핵심 엔티티 구현: `User`, `Post` (연관 관계 설정 완료)
- [x] JpaRepository 생성: `UserRepository`, `PostRepository`
- [x] `PostRequestDto`, `PostResponseDto` 생성 완료
- [x] `PostService` 구현 (CRUD 로직) 완료
- [x] `PostController` 구현 (API 엔드포인트) 완료
- [x] **Swagger (SpringDoc)** 의존성 추가 완료 (`build.gradle`)
- [x] **User 관련 DTO** 생성 완료 (`UserRequestDto`, `UserResponseDto`)
- [x] **Git 저장소 초기화** 및 통합 `.gitignore` 설정 완료
- [x] **UserService (Interface/Impl)** 구현 완료 (회원가입, 로그인 기본 로직)
- [x] **UserController** 구현 완료 (API 엔드포인트)
- [x] **API 테스트 완료** (User Signup/Login, Post CRUD 동작 확인 완료)
- [x] **Swagger UI 접속 테스트 완료** (`/swagger-ui/index.html`)
- [x] **Spring Security 및 JWT 적용 완료**
- [x] **로그인 엔드포인트 @RequestBody로 개선 완료**
- [x] **게시글 생성 시 인증 유저 자동 매핑 리팩토링 완료**
- [x] **H2 데이터베이스 유실 방지 설정 완료**

## 📅 현재 진행 중인 단계
- **Frontend 연동 준비**
  - API 명세 최종 확인 및 프론트엔드 작업 준비

## 📋 향후 할 일 (To-Do)
- [ ] **API 문서화 및 도구 설정**
  - [ ] **Docker** 환경 구성: `Dockerfile` 및 `docker-compose.yml` 작성
- [ ] **Frontend 연동**
  - [ ] API 호출부 작성 (Axios/React Query)
  - [ ] 게시글 목록 및 상세 페이지 UI 구현

---
*마지막 업데이트: 2026-03-18*
