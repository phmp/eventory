package com.proccorp.eventory.controllers;

import com.google.inject.Inject;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.storage.SchedulesRepository;

public class EventFinder {

    private SchedulesRepository repository;

    @Inject
    public EventFinder(SchedulesRepository repository) {
        this.repository = repository;
    }

    public Event getCurrent(String scheduleId){
        Schedule schedule = repository.get(scheduleId);
        return schedule.getEvents().stream().findFirst().orElseThrow();
    }

}
