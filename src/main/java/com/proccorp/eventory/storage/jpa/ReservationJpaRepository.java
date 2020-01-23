package com.proccorp.eventory.storage.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proccorp.eventory.model.persistence.ScheduleEntity;

public interface ReservationJpaRepository extends JpaRepository<ScheduleEntity, Long> {

}
