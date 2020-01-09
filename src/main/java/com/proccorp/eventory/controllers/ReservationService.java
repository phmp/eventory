package com.proccorp.eventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.model.User;
import com.proccorp.eventory.storage.SchedulesRepository;

public class ReservationService {

    private final SubscriptionExecutor subscriptionExecutor;
    private final SchedulesRepository schedulesRepository;

    @Autowired
    public ReservationService(SubscriptionExecutor subscriptionExecutor, SchedulesRepository schedulesRepository) {
        this.subscriptionExecutor = subscriptionExecutor;
        this.schedulesRepository = schedulesRepository;
    }

    public Reservation addPerson(String scheduleId, String eventId, User user) {
        Schedule schedule = schedulesRepository.get(scheduleId);
        Event event = schedule.getEvent(eventId);
        return subscriptionExecutor.subscribe(event, user);
    }

}
