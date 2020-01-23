package com.proccorp.eventory.storage.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.model.persistence.ScheduleEntity;
import com.proccorp.eventory.model.persistence.UserEntity;
import com.proccorp.eventory.storage.jpa.UserJpaRepository;

@Component
@Profile("db")
public class UserRepositoryInDataBase implements UserRepository{
    private UserJpaRepository jpaRepository;

    public UserRepositoryInDataBase(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override public List<User> getAll() {
        return jpaRepository.findAll().stream()
                .map(UserEntity::buildInternalObject)
                .collect(Collectors.toList());
    }

    @Override public User get(String id) {
        return findByExternalId(id).buildInternalObject();
    }

    @Override public void delete(String id) {
        jpaRepository.delete(findByExternalId(id));
    }

    @Override public void replace(String id, User element) {
        UserEntity userEntity = findByExternalId(id);
        userEntity.updateWith(element);
        jpaRepository.save(userEntity);
    }

    @Override public User add(User element) {
        UserEntity userEntity = findByExternalId(element.getId());
        userEntity.updateWith(element);
        return jpaRepository.save(userEntity).buildInternalObject();
    }

    private UserEntity findByExternalId(String id) {
        return jpaRepository.findAll().stream()
                .filter(userEntity -> id.equals(userEntity.getUserId()))
                .findFirst().orElse(null);
    }
}
