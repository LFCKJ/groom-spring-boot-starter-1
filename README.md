# Profile & Tech Stack API

## 🚩 프로젝트 개요
개발자의 프로필과 기술 스택을 관리하는 REST API 서비스입니다. Spring Boot를 기반으로 Layered Architecture를 적용하여 확장성과 유지보수성을 고려해 설계되었습니다.

### ⚠️ 최근 수정 사항 (Refactoring)
기존 코드에서 발견된 **치명적인 구조적 문제(실행 불가 오류)**를 해결하기 위해 다음과 같은 리팩토링을 진행했습니다.

1. **순환 참조(Circular Dependency) 제거**
   - **문제:** `PTSController`가 자기 자신을 주입받아 무한 루프에 빠져 애플리케이션이 실행되지 않음.
   - **해결:** 컨트롤러가 `PTSService`를 주입받도록 의존성 관계를 정상화했습니다.

2. **서비스 레이어 구조 개선 (Static 제거)**
   - **문제:** `PTSService`의 메서드들이 `static`으로 선언되어 스프링의 빈(Bean) 관리 및 의존성 주입(DI) 이점을 활용하지 못함. 또한, 메서드가 내부 로직 없이 자기 자신을 호출하여 `StackOverflowError` 발생.
   - **해결:** `static` 키워드를 제거하고 `@Service` 어노테이션을 적용하여 스프링 컨테이너가 관리하도록 변경했습니다. 재귀 호출 로직을 제거하고 기본 틀(Stub)을 마련했습니다.

3. **HTTP 메서드 매핑 정상화**
   - **문제:** 생성, 수정, 삭제 기능이 모두 `@GetMapping`으로 잘못 매핑되어 있어 RESTful 원칙에 위배되고 스프링 실행 시 매핑 충돌 발생.
   - **해결:** 각 기능에 맞게 `@PostMapping`, `@PutMapping`, `@DeleteMapping`으로 분리하여 API 명세를 준수하도록 수정했습니다.

## 🛠 기술 스택
- **Language**: Java 17
- **Framework**: Spring Boot 4.0.3
- **Database**: H2 (In-memory)
- **Build Tool**: Gradle
- **Library**: Lombok, Spring Data JPA

## 📂 프로젝트 구조
```
src/main/java/com/study/profile_stack_api/
├── controller/       # API 요청 처리 (PTSController)
├── service/          # 비즈니스 로직 (PTSService)
├── dao/              # 데이터 접근 (PTSLogDao -> Repository로 변경 예정)
├── dto/              # 데이터 전송 객체 (Request/Response)
└── ProfileStackApiApplication.java
```

## 🚀 실행 방법
1. **프로젝트 클론**
   ```bash
   git clone <repository-url>
   ```
2. **빌드 및 실행**
   ```bash
   ./gradlew bootRun
   ```

## 📝 API 엔드포인트
| Method | URI | 설명 |
|--------|-----|------|
| POST | `/api/v1/profile` | 프로필 생성 |
| GET | `/api/v1/profile` | 전체 프로필 조회 |
| GET | `/api/v1/profile/{id}` | 특정 프로필 조회 |
| PUT | `/api/v1/profile/{id}` | 프로필 수정 |
| DELETE | `/api/v1/profile/{id}` | 프로필 삭제 |

## 📅 향후 계획 (To-Do)
- [ ] JPA Entity (`Profile`, `TechStack`) 설계 및 구현
- [ ] Repository 인터페이스 도입 (Spring Data JPA)
- [ ] 실제 데이터베이스 연동 및 비즈니스 로직 완성
