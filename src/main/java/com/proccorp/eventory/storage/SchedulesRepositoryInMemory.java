package com.proccorp.eventory.storage;

import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.Schedule;

@Component
public class SchedulesRepositoryInMemory extends RepositoryInMemory<Schedule> implements SchedulesRepository {

}
