package com.proccorp.eventory.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<EventView> eventViews = Optional.ofNullable(schedule)
                .map(Schedule::getEvents)
                .orElse(new ArrayList<>())
                .stream()
                .map(eventMapper::toView)
                .collect(toList());
        return new ScheduleView(schedule.getId(),
                eventViews,
                schedule.getMaxNumberOfPeople(),
                schedule.getHost(),
                schedule.getLocation(),
                schedule.getDescription());
    }

}
