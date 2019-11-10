package com.proccorp.eventory.model;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Event {
    private Schedule schedule;
    private ZonedDateTime zonedDateTime;
    private List<Reservation> reservations;
    public boolean isFull(){
        return schedule.getMaxNumberOfPeople() - reservations.size() <= 0;
    }
}
