package com.proccorp.eventory.model.api.schedules;

import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.internal.User;

import java.util.List;

public class ScheduleView {

    private String id;
    private List<EventView> events;
    private int maxNumberOfPeople;
    private User host;
    private String location;
    private String description;

}