package com.proccorp.eventory.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.proccorp.eventory.model.EventMapper;
import com.proccorp.eventory.model.api.events.EventCreate;
import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.reservation.ReservationView;
import com.proccorp.eventory.model.api.users.UserView;
import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.service.events.EventFinder;
import com.proccorp.eventory.service.events.ReservationService;
import com.proccorp.eventory.storage.schedule.SchedulesRepository;

@RestController
@RequestMapping(value = "/schedules/{scheduleId}/events")
public class EventController {

    private SchedulesRepository schedulesRepository;
    private EventFinder eventFinder;
    private ReservationService reservationService;
    private EventMapper eventMapper;

    public EventController(SchedulesRepository schedulesRepository,
            EventFinder eventFinder, ReservationService reservationService,
            EventMapper eventMapper) {
        this.schedulesRepository = schedulesRepository;
        this.eventFinder = eventFinder;
        this.reservationService = reservationService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("/{eventId}")
    public EventView getEvent(@PathVariable String scheduleId,@PathVariable String eventId){
        Schedule schedule = schedulesRepository.get(scheduleId);
        Event event = schedule.getEvent(eventId);
        return eventMapper.toView(event);
    }

    @GetMapping("/current")
    public EventView getCurrentEvent(@PathVariable String scheduleId){
        Event current = eventFinder.getCurrent(scheduleId);
        return eventMapper.toView(current);
    }

    @PostMapping("")
    public EventView createEvent(@PathVariable String scheduleId, @RequestBody String eventCreate){
        Schedule schedule = schedulesRepository.get(scheduleId);
        EventCreate eventCreate1 = new Gson().fromJson(eventCreate, EventCreate.class);
        Event event = eventMapper.toInternal(eventCreate1, schedule);
        schedule.addEvent(event);
        schedulesRepository.replace(scheduleId, schedule);
        return eventMapper.toView(event);
    }

//
//    @PostMapping("/reservations")
//    public ReservationView createReservation(@PathVariable String scheduleId, String eventId, @RequestBody UserView user){
//
//        reservationService.addPerson(scheduleId, eventId, user);
//        List<Reservation> reservations = getEvent(scheduleId, eventId).getReservations();
//        return reservations.stream().map(reservation -> reservationMapper.toView(reservation));
//    }

}
