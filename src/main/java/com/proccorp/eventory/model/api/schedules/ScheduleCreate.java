package com.proccorp.eventory.model.api.schedules;

import com.proccorp.eventory.model.api.users.UserCreate;

import lombok.Data;

@Data
public class ScheduleCreate {

    private final int maxNumberOfPeople;
    private final String hostId;
    private final String location;
    private final String description;

}