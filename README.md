# SchedulerV2

SchedulerV2는 일정 관리를 위한 Spring Boot 기반의 백엔드 프로젝트입니다. JWT 인증 방식을 활용하여 사용자 인증을 처리하며, JPA를 사용하여 데이터를 관리합니다.

## 기술 스택

- **언어**: Java 17+
- **프레임워크**: Spring Boot 3+
- **데이터베이스**: MySQL
- **ORM**: Spring Data JPA
- **빌드 툴**: Gradle
- **보안**: JWT (세션 기반 저장)

## 주요 기능

1. **일정 관리**: 사용자는 자신의 일정을 생성, 수정, 삭제할 수 있습니다.
2. **댓글 관리**: 사용자는 자신의 댓글을 생성, 수정, 삭제할 수 있습니다.
3. **인증 및 인가**: Spring Security 없이 Servlet Filter 및 JWT 기반 인증을 구현하였습니다.
4. **사용자 관리**:
    - 일정 작성자를 위한 `user` 테이블을 활용
    - 회원가입 및 로그인 기능 제공 (JWT 기반)

## 설치 및 실행 방법

1. **프로젝트 클론**

   ```sh
   git clone https://github.com/ijieun0123/schedulerV2.git
   cd schedulerV2
   ```

2. **필수 환경 변수 설정** (`application.properties` 수정)

3. **의존성 설치 및 실행**

   ```sh
   ./gradlew bootRun  # Gradle 사용 시
   ```

4. **API 테스트**

    - Postman 또는 Swagger UI 사용

## ERD 설계
![Image](https://github.com/user-attachments/assets/36c9d6a9-0d68-43ed-bb9e-bc0cef441a84)

## API 명세

### 1. 유저 API

#### 회원가입

- **POST** `/users/signup`
- 요청 예시:
  ```json
  {
    "username": "username",
    "email": "ijieun@gmail.com",
    "password": "password123@"
  }
  ```
- 응답 예시:
  ```json
  {
    "id": 1,
    "username": "username",
    "email": "ijieun@gmail.com",
    "scheduleSize": 0,
    "commentSize": 0,
    "createdAt": "2025-02-07T20:35:17.110178",
    "modifiedAt": "2025-02-07T20:35:17.110178"
  }
  ```

#### 로그인

- **POST** `/users/login`
- 요청 예시:
  ```json
  {
    "email": "ijieun@gmail.com",
    "password": "password123@"
  }
  ```
- 응답 예시:
  ```json
  {
    "token": "jwt-token-here"
  }
  ```

#### 회원 조회

- **GET** `/users/{id}`
- 응답 예시:
  ```json
  {
    "id": 1,
    "username": "username",
    "email": "ijieun@gmail.com",
    "scheduleSize": 0,
    "commentSize": 0,
    "createdAt": "2025-02-07T19:46:41.159777",
    "modifiedAt": "2025-02-07T19:46:41.159777"
  }
  ```

#### 회원 전체 조회

- **GET** `/users?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
    [
        {
            "id": 1,
            "username": "username",
            "email": "ijieun@gmail.com",
            "scheduleSize": 0,
            "commentSize": 0,
            "createdAt": "2025-02-07T19:46:41.159777",
            "modifiedAt": "2025-02-07T19:46:41.159777"
        }
    ]
  ```

#### 회원 수정

- **PATCH** `/users/{id}`
- 요청 예시:
  ```json
    {
      "username": "수정"
    }
  ```
- 응답 예시:
  ```json
    {
      "id": 1,
      "username": "수정",
      "email": "ijieun@gmail.com",
      "scheduleSize": 0,
      "commentSize": 0,
      "createdAt": "2025-02-07T19:46:41.159777",
      "modifiedAt": "2025-02-07T19:46:41.159777"
    }
  ```

#### 회원 삭제

- **DELETE** `/users/{id}`

---

### 2. 일정 관리 API

#### 일정 생성

- **POST** `/schedules`
- 요청 예시:
  ```json
    {
      "email":"ijieun@gmail.com",
      "title": "title",
      "contents": "content"
    }
  ```
- 응답 예시:
  ```json
    {
      "id": 1,
      "title": "title",
      "contents": "content",
      "username": "username",
      "commentSize": 0,
      "createdAt": "2025-02-07T19:57:47.829336",
      "modifiedAt": "2025-02-07T19:57:47.829336"
    }
  ```

#### 일정 조회

- **GET** `/schedules/{id}`
- 응답 예시:
```json
  {
    "id": 1,
    "title": "title",
    "contents": "content",
    "username": "username",
    "commentSize": 0,
    "createdAt": "2025-02-07T19:57:47.829336",
    "modifiedAt": "2025-02-07T19:57:47.829336"
  }
```

#### 일정 조회 (by userId)

- **GET** `/schedules/user/{userId}?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
  [
    {
      "id": 1,
      "title": "title",
      "contents": "content",
      "username": "username",
      "commentSize": 0,
      "createdAt": "2025-02-07T19:57:47.829336",
      "modifiedAt": "2025-02-07T19:57:47.829336"
    }
  ]
  ```

#### 일정 전체 조회

- **GET** `/schedules?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
  [
    {
      "id": 1,
      "title": "title",
      "contents": "content",
      "username": "username",
      "commentSize": 0,
      "createdAt": "2025-02-07T19:57:47.829336",
      "modifiedAt": "2025-02-07T19:57:47.829336"
    }
  ]
  ```

#### 일정 수정

- **PATCH** `/schedules/{id}`
- 요청 예시:
  ```json
    {
      "title": "title update",
      "contents": "contents update"
    }
  ```
- 응답 예시:
  ```json
    {
      "id": 1,
      "title": "title update",
      "contents": "contents update",
      "username": "수정",
      "commentSize": 0,
      "createdAt": "2025-02-07T19:46:53.837209",
      "modifiedAt": "2025-02-07T19:46:53.837209"
    }
  ```

#### 일정 삭제

- **DELETE** `/schedules/{id}`

---

### 3. 댓글 관리 API

#### 댓글 생성

- **POST** `/comments`
- 요청 예시:
  ```json
    {
      "contents": "comment contents",
      "schedule_id": 1,
      "user_id": 1
    }
  ```
- 응답 예시:
  ```json
    {
      "id": 1,
      "contents": "comment contents",
      "scheduleTitle": "title",
      "username": "username",
      "createdAt": "2025-02-07T19:57:52.614234",
      "updatedAt": "2025-02-07T19:57:52.614234"
    }
  ```

#### 댓글 조회

- **GET** `/comments/{id}`
- 응답 예시:
  ```json
    {
      "id": 1,
      "contents": "comment contents",
      "scheduleTitle": "title",
      "username": "username",
      "createdAt": "2025-02-07T19:57:52.614234",
      "updatedAt": "2025-02-07T19:57:52.614234"
    }
  ```

#### 댓글 조회 (by scheduleId)

- **GET** `/comments/schedule/{scheduleId}?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
    [
      {
        "id": 1,
        "contents": "comment contents",
        "scheduleTitle": "title update",
        "username": "수정",
        "createdAt": "2025-02-07T19:47:41.904048",
        "updatedAt": "2025-02-07T19:47:41.904048"
      }
    ]
  ```

#### 댓글 조회 (by userId)

- **GET** `/comments/user/{userId}?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
    [
      {
        "id": 1,
        "contents": "comment contents",
        "scheduleTitle": "title update",
        "username": "수정",
        "createdAt": "2025-02-07T19:47:41.904048",
        "updatedAt": "2025-02-07T19:47:41.904048"
      }
    ]
  ```

#### 댓글 전체 조회

- **GET** `/comments?pageNumber={pageNumber}&pageSize={pageSize}`
- 응답 예시:
  ```json
    [
      {
        "id": 1,
        "contents": "comment contents",
        "scheduleTitle": "title update",
        "username": "수정",
        "createdAt": "2025-02-07T19:47:41.904048",
        "updatedAt": "2025-02-07T19:47:41.904048"
      }
    ]
  ```

#### 댓글 수정

- **PATCH** `/comments/{id}`
- 요청 예시:
  ```json
    {
      "contents": "contents update"
    }
  ```
- 응답 예시:
  ```json
    {
      "id": 1,
      "contents": "contents update",
      "scheduleTitle": "title",
      "username": "username",
      "createdAt": "2025-02-07T19:57:52.614234",
      "updatedAt": "2025-02-07T19:57:52.614234"
    }
  ```

#### 댓글 삭제

- **DELETE** `/comments/{id}`

---

### 4. 예외 처리

#### 잘못된 요청

- **응답 코드**: `400 Bad Request`

#### 인증 실패

- **응답 코드**: `401 Unauthorized`

#### 리소스 없음

* **응답 코드**: `404 Not Found`

#### 서버 오류

- **응답 코드**: `500 Internal Server Error`


