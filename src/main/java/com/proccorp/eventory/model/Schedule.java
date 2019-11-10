package com.proccorp.eventory.model;

import java.util.List;

import lombok.Data;

@Data
public class Schedule {
    private List<Event> events;
    private int maxNumberOfPeople;
    private Person host;
    private String location;
    private String description;
}
