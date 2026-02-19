package com.study.profile_stack_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class TechStack {
    @Id
    private Long id;
    private String name;
    private String proficiency;
    private String category;
    private Integer yearsofexp;
    private Integer profile_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    @ManyToOne
    Profile profile;

    //기본 생성자
    public TechStack() {}
    //전체 필드
    public TechStack(Long id, String name,
                     String proficiency, String category,
                     Integer yearsofexp, Integer profile_id,
                     LocalDateTime created_at, LocalDateTime updated_at){
    this.id = id;
    this.name = name;
    this.proficiency = proficiency;
    this.category = category;
    this.yearsofexp = yearsofexp;
    this.profile_id = profile_id;
    this.created_at = created_at;
    this.updated_at = updated_at;

    }

}
