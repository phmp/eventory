package com.proccorp.eventory.storage.schedule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.model.persistence.ScheduleEntity;
import com.proccorp.eventory.model.persistence.UserEntity;
import com.proccorp.eventory.storage.jpa.ScheduleJpaRepository;
import com.proccorp.eventory.storage.jpa.UserJpaRepository;

@Component
@Profile("db")
public class SchedulesRepositoryInDataBase implements SchedulesRepository {

    private ScheduleJpaRepository scheduleJpaRepository;
    private UserJpaRepository userJpaRepository;

    public SchedulesRepositoryInDataBase(ScheduleJpaRepository scheduleJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override public List<Schedule> getAll() {
        return scheduleJpaRepository.findAll().stream()
                .map(ScheduleEntity::toInternal)
                .collect(Collectors.toList());
    }

    @Override public Schedule get(String id) {
        return scheduleJpaRepository.findByScheduleId(id)
                .toInternal();
    }

    @Override public void delete(String id) {
        ScheduleEntity byScheduleId = scheduleJpaRepository.findByScheduleId(id);
        scheduleJpaRepository.delete(byScheduleId);
    }

    @Override public void replace(String id, Schedule element) {
        ScheduleEntity scheduleEntity = scheduleJpaRepository.findByScheduleId(id);
        scheduleEntity.updateWith(element);
        scheduleJpaRepository.save(scheduleEntity);
    }

    @Override public Schedule add(Schedule element) {
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        User host = element.getHost();
        UserEntity firstByUserId = userJpaRepository.findFirstByUserId(host.getId());
        scheduleEntity.updateWith(element);
        scheduleEntity.setHost(firstByUserId);
        return scheduleJpaRepository.save(scheduleEntity).toInternal();
    }

}
