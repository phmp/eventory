package com.proccorp.eventory.service.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.storage.SchedulesRepository;

@Service
public class EventFinder {

    private final SchedulesRepository repository;
    private final CurrentEventSelector eventSelector;

    @Autowired
    public EventFinder(SchedulesRepository repository,
            CurrentEventSelector eventSelector) {
        this.repository = repository;
        this.eventSelector = eventSelector;
    }

    public Event getCurrent(String scheduleId) {
        Schedule schedule = repository.get(scheduleId);
        List<Event> events = schedule.getEvents();
        return eventSelector.getCurrent(events);
    }

}
