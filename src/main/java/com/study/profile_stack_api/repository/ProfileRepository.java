package com.study.profile_stack_api.repository;

import com.study.profile_stack_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class ProfileRepository implements JpaRepository<Profile, Long> {

    public List<Profile> findAll(){

    }
}
