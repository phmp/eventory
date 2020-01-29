package com.proccorp.eventory.model.api.schedules;

import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.internal.User;

import java.util.List;

import lombok.Data;

@Data
public class ScheduleView {

    private final String id;
    private final List<EventView> events;
    private final int maxNumberOfPeople;
    private final User host;
    private final String location;
    private final String description;

}