package com.proccorp.eventory.service.events;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proccorp.eventory.date.SimpleTimeProvider;
import com.proccorp.eventory.model.Event;

@Service
public class CurrentEventSelector {
    private final SimpleTimeProvider timeProvider;

    @Autowired
    public CurrentEventSelector(SimpleTimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Event getCurrent(List<Event> eventList){
        return eventList.stream()
                .sorted()
                .filter(this::isNotPassed)
                .findFirst()
                .orElseThrow();
    }

    private boolean isNotPassed(Event event) {
        LocalDate eventDate = LocalDate.from(event.getZonedDateTime());
        LocalDate currentDate = LocalDate.from(timeProvider.zonedNow());
        return !eventDate.isBefore(currentDate);
    }

}
