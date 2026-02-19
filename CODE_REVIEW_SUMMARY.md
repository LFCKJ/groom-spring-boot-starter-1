# 코드 리뷰 및 리팩토링 제안 정리

이 문서는 `Profile.java`와 `TechStack.java` 엔티티 클래스에 대한 코드 리뷰 결과와 수정 제안 사항을 정리한 것입니다.

## 1. 주요 개선 포인트

### 1.1 Lombok 라이브러리 활용
- **현상**: Getter, Setter, 생성자 코드가 직접 작성되어 있어 클래스가 불필요하게 길어짐.
- **개선**: `lombok` 의존성을 활용하여 `@Getter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder` 어노테이션으로 코드를 간소화하고 가독성을 높임.

### 1.2 JPA Naming Convention 준수
- **현상**: Java 필드명에 `snake_case`(`yearsofexp`, `created_at`)가 사용됨.
- **개선**: Java의 표준인 `camelCase`(`yearsOfExperience`, `createdAt`)로 변경하고, 필요한 경우 `@Column(name = "...")`을 사용하여 DB 컬럼과 매핑.

### 1.3 연관 관계 매핑 최적화 (Entity Mapping)
- **현상**: `TechStack` 엔티티에서 `profile_id`를 단순히 `Long` 타입의 컬럼으로 관리하고 있음.
- **개선**: 객체지향적인 설계를 위해 `Profile` 엔티티와 직접 연관 관계(`@ManyToOne`)를 맺고 `@JoinColumn`을 사용. 성능을 위해 `FetchType.LAZY` 권장.

### 1.4 JPA Auditing 적용
- **현상**: 생성일(`createdAt`), 수정일(`updatedAt`)을 수동으로 관리해야 함.
- **개선**: `@CreatedDate`, `@LastModifiedDate`와 `@EntityListeners(AuditingEntityListener.class)`를 사용하여 자동으로 관리.

---

## 2. 수정된 코드 제안

### 2.1 Profile.java

```java
package com.study.profile_stack_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 protected로 제한
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 활성화
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String position;
    private String bio;
    private Integer careerYears;
    private String githubUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 양방향 매핑 (선택 사항, 필요 시 추가)
    // @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    // private List<TechStack> techStacks = new ArrayList<>();

    @Builder
    public Profile(String name, String email, String position, String bio, Integer careerYears, String githubUrl) {
        this.name = name;
        this.email = email;
        this.position = position;
        this.bio = bio;
        this.careerYears = careerYears;
        this.githubUrl = githubUrl;
    }
}
```

### 2.2 TechStack.java

```java
package com.study.profile_stack_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String proficiency;
    private String category;

    // DB 컬럼명은 snake_case일지라도 Java 필드는 camelCase 사용
    @Column(name = "years_of_experience") 
    private Integer yearsOfExperience; 

    // 객체 지향적인 연관 관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public TechStack(String name, String proficiency, String category, Integer yearsOfExperience, Profile profile) {
        this.name = name;
        this.proficiency = proficiency;
        this.category = category;
        this.yearsOfExperience = yearsOfExperience;
        this.profile = profile;
    }
}
```

---

## 3. 추가 설정 필요 사항

JPA Auditing(`@CreatedDate`, `@LastModifiedDate`)이 정상 작동하려면 메인 애플리케이션 클래스에 어노테이션 설정이 필요합니다.

**`ProfileStackApiApplication.java`**
```java
@EnableJpaAuditing // 이 어노테이션 추가
@SpringBootApplication
public class ProfileStackApiApplication {
    // ...
}
```
