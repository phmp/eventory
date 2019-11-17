package com.proccorp.eventory.model;

import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Schedule {
    private List<Event> events;
    private int maxNumberOfPeople;
    private User host;
    private String location;
    private String description;

    public Event getEvent(String eventId){
        return events.stream().filter(event -> Objects.equals(eventId, event.getId()))
                .findFirst().orElseThrow();
    }
}
