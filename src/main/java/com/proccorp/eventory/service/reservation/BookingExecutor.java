package com.proccorp.eventory.service.reservation;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

import com.proccorp.eventory.date.SimpleTimeProvider;
import com.proccorp.eventory.model.internal.Event;
import com.proccorp.eventory.model.internal.Reservation;
import com.proccorp.eventory.model.internal.User;

@Component
public class BookingExecutor {

    private final SimpleTimeProvider timeProvider;

    public BookingExecutor(SimpleTimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Reservation book(Event event, User user){
        if (event.isFull()) {
            throw new RuntimeException("Reservation failed. Event full.");
        } else {
            ZonedDateTime now = timeProvider.zonedNow();
            Reservation reservation = new Reservation("","created", now, user);
            event.getReservations().add(reservation);
            return reservation;
        }
    }

}
