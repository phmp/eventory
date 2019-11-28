package com.proccorp.eventory.controllers;

import java.time.ZonedDateTime;

import com.google.inject.Inject;
import com.proccorp.eventory.date.TimeProvider;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.User;

public class SubscriptionExecutor {

    private final TimeProvider timeProvider;

    @Inject
    public SubscriptionExecutor(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Reservation subscribe(Event event, User user){
        if (!event.isFull()) {
            ZonedDateTime now = timeProvider.zonedNow();
            Reservation reservation = new Reservation(false, now, user);
            event.getReservations().add(reservation);
            return reservation;
        } else {
            throw new RuntimeException("Reservation failed. Event full.");
        }
    }

}
