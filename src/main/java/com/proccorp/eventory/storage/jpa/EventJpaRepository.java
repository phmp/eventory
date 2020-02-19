package com.proccorp.eventory.storage.jpa;

import com.proccorp.eventory.model.persistence.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proccorp.eventory.model.persistence.ScheduleEntity;

public interface EventJpaRepository extends JpaRepository<EventEntity, Long> {

}
