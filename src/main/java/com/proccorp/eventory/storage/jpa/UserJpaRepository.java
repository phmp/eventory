package com.proccorp.eventory.storage.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proccorp.eventory.model.persistence.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByUserId(String userId);
}
