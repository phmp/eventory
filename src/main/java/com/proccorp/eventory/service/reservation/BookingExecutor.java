package com.proccorp.eventory.service.reservation;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.date.TimeProvider;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.User;

@Component
public class BookingExecutor {

    private final TimeProvider timeProvider;

    @Autowired
    public BookingExecutor(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Reservation book(Event event, User user){
        if (event.isFull()) {
            throw new RuntimeException("Reservation failed. Event full.");
        } else {
            ZonedDateTime now = timeProvider.zonedNow();
            Reservation reservation = new Reservation("",false, now, user);
            event.getReservations().add(reservation);
            return reservation;
        }
    }

}
