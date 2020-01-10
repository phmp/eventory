package com.proccorp.eventory.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.model.User;
import com.proccorp.eventory.service.events.EventFinder;
import com.proccorp.eventory.service.events.ReservationService;
import com.proccorp.eventory.storage.SchedulesRepository;

@Controller
@RestController
@RequestMapping(value = "/schedules", produces = APPLICATION_JSON_VALUE)
public class ScheduleController {

    private final SchedulesRepository schedulesRepository;
    private final EventFinder eventFinder;
    private final ReservationService reservationService;

    @Autowired
    public ScheduleController(SchedulesRepository schedulesRepository,
            EventFinder eventFinder, ReservationService reservationService) {
        this.schedulesRepository = schedulesRepository;
        this.eventFinder = eventFinder;
        this.reservationService = reservationService;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Schedule> getSchedules(){
        return schedulesRepository.getAll();
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable String id){
        return schedulesRepository.get(id);
    }

    @PostMapping("/")
    public Schedule createSchedule(@RequestBody Schedule body){
        return schedulesRepository.add(body);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable String id, @RequestBody Schedule body){
        schedulesRepository.replace(id, body);
        return schedulesRepository.get(id);
    }

    @GetMapping("/{scheduleId}/events/{eventId}")
    public Event getEvent(String scheduleId, String eventId){
        return schedulesRepository.get(scheduleId).getEvent(eventId);
    }

    @GetMapping("/{scheduleId}/events/current")
    public Event getCurrentEvent(String scheduleId){
        return eventFinder.getCurrent(scheduleId);
    }

    @PostMapping("/{scheduleId}/events/{eventId}/reservations")
    public List<Reservation> createReservation(String scheduleId, String eventId, @RequestBody User user){
        reservationService.addPerson(scheduleId, eventId, user);
        return getEvent(scheduleId, eventId).getReservations();
    }

        // sql lite
        // DDL -
        // migracja od dnia zero
        // framework ktory robi migracje
        // https://letsencrypt.org/
}
