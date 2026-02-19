package com.study.profile_stack_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;

@Entity
public class Profile {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String email;
  private String position;
  private String bio;
  private Integer careerYears;
  private String githubUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updateAt;
  //기본 생성자
    public Profile(){}
  //전체 필드
  public Profile(Long id, String name,
                 String email, String position,
                 String bio, Integer careerYears,
                 String githubUrl,LocalDateTime createdAt,
                 LocalDateTime updateAt){
      this.id = id;
      this.name = name;
      this.email = email;
      this.position = position;
      this.bio = bio;
      this.careerYears = careerYears;
      this.githubUrl = githubUrl;
      this.createdAt = createdAt;
      this.updateAt = updateAt;

  }
}
