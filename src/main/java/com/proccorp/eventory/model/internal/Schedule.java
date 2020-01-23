package com.proccorp.eventory.model.internal;

import java.util.List;
import java.util.Objects;

import com.proccorp.eventory.model.persistence.ScheduleEntity;

import lombok.Data;

@Data
public class Schedule extends IndexedObject {
    private List<Event> events;
    private int maxNumberOfPeople;
    private User host;
    private String location;
    private String description;

    public Schedule() {
        super();
    }

    public Schedule(List<Event> events, int maxNumberOfPeople, User host, String location, String description) {
        super();
        this.events = events;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.host = host;
        this.location = location;
        this.description = description;
    }

    public Schedule(String id, List<Event> events, int maxNumberOfPeople, User host, String location, String description) {
        super(id);
        this.events = events;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.host = host;
        this.location = location;
        this.description = description;
    }

    public Event getEvent(String eventId){
        return events.stream().filter(event -> Objects.equals(eventId, event.getId()))
                .findFirst().orElseThrow();
    }

    @Override public String toString() {
        return "Schedule{" +
                "events=" + events +
                ", maxNumberOfPeople=" + maxNumberOfPeople +
                ", host=" + host +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
