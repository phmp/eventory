package com.proccorp.eventory.storage.schedule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.persistence.ScheduleEntity;
import com.proccorp.eventory.storage.RepositoryInMemory;
import com.proccorp.eventory.storage.jpa.ScheduleJpaRepository;

@Component
@Profile("db")
public class SchedulesRepositoryInDataBase implements SchedulesRepository {

    ScheduleJpaRepository jpaRepository;

    public SchedulesRepositoryInDataBase(ScheduleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override public List<Schedule> getAll() {
        return jpaRepository.findAll().stream()
                .map(ScheduleEntity::buildInternalObject)
                .collect(Collectors.toList());
    }

    @Override public Schedule get(String id) {
        return findByExternalId(id)
                .buildInternalObject();
    }

    @Override public void delete(String id) {
        jpaRepository.delete(findByExternalId(id));
    }

    @Override public void replace(String id, Schedule element) {
        ScheduleEntity scheduleEntity = findByExternalId(id);
        scheduleEntity.updateWith(element);
        jpaRepository.save(scheduleEntity);
    }

    @Override public Schedule add(Schedule element) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.updateWith(element);
        return jpaRepository.save(scheduleEntity).buildInternalObject();
    }

    private ScheduleEntity findByExternalId(String id) {
        return jpaRepository.findAll().stream()
                .filter(scheduleEntity -> id.equals(scheduleEntity.getScheduleId()))
                .findFirst()
                .orElse(null);
    }
}
