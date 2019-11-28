package com.proccorp.eventory.controllers;

import com.google.inject.Inject;
import com.proccorp.eventory.model.*;
import com.proccorp.eventory.storage.SchedulesRepository;

public class SubscriptionController {

    private final SubscriptionExecutor subscriptionExecutor;
    private final SchedulesRepository schedulesRepository;

    @Inject
    public SubscriptionController(SubscriptionExecutor subscriptionExecutor, SchedulesRepository schedulesRepository) {
        this.subscriptionExecutor = subscriptionExecutor;
        this.schedulesRepository = schedulesRepository;
    }

    public Reservation addPerson(SubscriptionRequest request){
        User user = request.getUser();
        Schedule schedule = schedulesRepository.get(request.getScheduleId());
        Event event = schedule.getEvent(request.getEventId());

        return subscriptionExecutor.subscribe(event, user);
    }

}
