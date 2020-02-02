package com.proccorp.eventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proccorp.eventory.model.ScheduleMapper;
import com.proccorp.eventory.model.api.schedules.ScheduleCreate;
import com.proccorp.eventory.model.api.schedules.ScheduleView;
import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Reservation;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.service.events.EventFinder;
import com.proccorp.eventory.service.events.ReservationService;
import com.proccorp.eventory.storage.schedule.SchedulesRepository;
import com.proccorp.eventory.storage.user.UserRepository;

@RestController
@RequestMapping(value = "/schedules")
public class ScheduleController {

    private final SchedulesRepository schedulesRepository;
    private final EventFinder eventFinder;
    private final ReservationService reservationService;
    private final ScheduleMapper scheduleMapper;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleController(SchedulesRepository schedulesRepository,
            EventFinder eventFinder, ReservationService reservationService,
            ScheduleMapper scheduleMapper, UserRepository userRepository) {
        this.schedulesRepository = schedulesRepository;
        this.eventFinder = eventFinder;
        this.reservationService = reservationService;
        this.scheduleMapper = scheduleMapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Schedule> getSchedules(){
        return schedulesRepository.getAll();
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable String id){
        return schedulesRepository.get(id);
    }

    @PostMapping
    public ScheduleView createSchedule(@RequestBody ScheduleCreate body){
        User host = userRepository.get(body.getHostId());
        Schedule schedule = scheduleMapper.toInternal(body, host);
        Schedule addedSchedule = schedulesRepository.add(schedule);
        return scheduleMapper.toView(addedSchedule);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable String id, @RequestBody Schedule body){
        schedulesRepository.replace(id, body);
        return schedulesRepository.get(id);
    }

}
