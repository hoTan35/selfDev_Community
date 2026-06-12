# SelfDev Community

개발자를 위한 자기계발 커뮤니티 플랫폼입니다.  
사용자가 게시글을 작성하고 다른 사람들과 지식을 나눌 수 있는 풀스택 웹 애플리케이션입니다.

## 기술 스택

| 영역 | 기술 |
|------|------|
| Frontend | React 19, TypeScript, Vite 8 |
| Backend | Spring Boot 3.4.3, Java 21 |
| 인증 | Spring Security, JWT (jjwt 0.12.5) |
| DB | H2 (in-memory), Spring Data JPA |
| API 문서 | SpringDoc OpenAPI (Swagger UI) |
| 빌드 도구 | Gradle 8.13, npm |

## 주요 기능

- 회원가입 / 로그인 (JWT 토큰 기반 인증)
- 게시글 작성, 조회, 수정, 삭제 (CRUD)
- Spring Security 기반 인증/인가
- Swagger UI를 통한 API 문서 자동화

## 프로젝트 구조

```
selfDev_community/
├── backend/                  # Spring Boot 서버
│   └── src/main/java/com/example/selfdev/
│       ├── controller/       # PostController, UserController
│       ├── dto/              # 요청/응답 DTO
│       ├── entity/           # Post, User 엔티티
│       ├── repository/       # JPA Repository
│       ├── security/         # JWT 필터, Spring Security 설정
│       └── service/          # 비즈니스 로직
└── frontend/                 # React + TypeScript 클라이언트
    └── src/
        └── App.tsx
```

## 시작하기

### 사전 요구사항

- Java 21+
- Node.js 18+
- Gradle (wrapper 포함으로 별도 설치 불필요)

### 백엔드 실행

```bash
cd backend
./gradlew bootRun
```

서버가 시작되면 아래 주소에서 접근 가능합니다.

- API 서버: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- H2 콘솔: `http://localhost:8080/h2-console`

### 프론트엔드 실행

```bash
cd frontend
npm install
npm run dev
```

브라우저에서 `http://localhost:5173` 으로 접근합니다.

### 환경 변수

`backend/src/main/resources/application.properties`에서 아래 값을 환경에 맞게 수정하세요.

```properties
jwt.secret=YOUR_256BIT_SECRET_KEY
jwt.expiration=86400000
```

## API 엔드포인트

| Method | URL | 설명 |
|--------|-----|------|
| POST | `/api/users/signup` | 회원가입 |
| POST | `/api/users/login` | 로그인 |
| GET | `/api/posts` | 게시글 목록 조회 |
| POST | `/api/posts` | 게시글 작성 (인증 필요) |
| PUT | `/api/posts/{id}` | 게시글 수정 (인증 필요) |
| DELETE | `/api/posts/{id}` | 게시글 삭제 (인증 필요) |

> 전체 API 명세는 Swagger UI에서 확인하세요.

## 기여 방법

1. 레포지토리를 Fork합니다
2. 새 브랜치를 생성합니다 (`git checkout -b feature/기능명`)
3. 변경사항을 커밋합니다 (`git commit -m 'feat: 기능 추가'`)
4. 브랜치에 Push합니다 (`git push origin feature/기능명`)
5. Pull Request를 열어주세요

## 라이선스

MIT License
