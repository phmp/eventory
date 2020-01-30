package com.proccorp.eventory.model;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.schedules.ScheduleCreate;
import com.proccorp.eventory.model.api.schedules.ScheduleView;
import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;

@Component
public class ScheduleMapper {

    private final EventMapper eventMapper;

    public ScheduleMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public Schedule toInternal(ScheduleCreate request, User host){
        return new Schedule(null, request.getMaxNumberOfPeople(), host, request.getLocation(), request.getDescription());
    }

    public ScheduleView toView(Schedule schedule){
        List<EventView> eventViews = schedule.getEvents().stream().map(eventMapper::toView).collect(toList());
        return new ScheduleView(schedule.getId(),
                eventViews,
                schedule.getMaxNumberOfPeople(),
                schedule.getHost(),
                schedule.getLocation(),
                schedule.getDescription());
    }

}
