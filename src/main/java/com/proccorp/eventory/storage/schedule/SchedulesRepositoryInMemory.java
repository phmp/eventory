package com.proccorp.eventory.storage.schedule;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.storage.RepositoryInMemory;

@Component
@Profile("memory")
public class SchedulesRepositoryInMemory extends RepositoryInMemory<Schedule> implements SchedulesRepository {

}
