package com.proccorp.eventory.model.api.schedules;

import com.proccorp.eventory.model.api.users.UserRaw;

import lombok.Data;

@Data
public class ScheduleCreate {

    private final int maxNumberOfPeople;
    private final UserRaw host;
    private final String location;
    private final String description;

}