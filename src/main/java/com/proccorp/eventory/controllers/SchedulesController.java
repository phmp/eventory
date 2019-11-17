package com.proccorp.eventory.controllers;

import com.google.inject.Inject;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.storage.SchedulesRepository;

public class SchedulesController {

    private SchedulesRepository repository;

    @Inject
    public SchedulesController(SchedulesRepository repository) {
        this.repository = repository;
    }

    public Event getCurrent(String scheduleId){
        Schedule schedule = repository.get(scheduleId);
        return schedule.getEvents().stream().findFirst().orElseThrow();
    }

}
