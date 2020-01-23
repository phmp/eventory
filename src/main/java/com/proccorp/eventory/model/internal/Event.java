package com.proccorp.eventory.model.internal;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Event extends IndexedObject implements Comparable<Event>{
    private Schedule schedule;
    private ZonedDateTime zonedDateTime;
    private List<Reservation> reservations;

    public Event() {
        super();
    }

    public Event(Schedule schedule, ZonedDateTime zonedDateTime,
            List<Reservation> reservations) {
        super();
        this.schedule = schedule;
        this.zonedDateTime = zonedDateTime;
        this.reservations = reservations;
    }

    public boolean isFull(){
        return reservations.size() >= schedule.getMaxNumberOfPeople();
    }

    @Override public int compareTo(Event event) {
        return zonedDateTime.compareTo(event.getZonedDateTime());
    }

}
