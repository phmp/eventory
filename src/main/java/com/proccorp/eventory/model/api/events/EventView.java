package com.proccorp.eventory.model.api.events;

import com.proccorp.eventory.model.api.reservation.ReservationView;
import com.proccorp.eventory.model.internal.Reservation;
import com.proccorp.eventory.model.internal.Schedule;

import lombok.Data;

import java.util.List;

@Data
public class EventView {
    private final String id;
    private final String zonedDateTime;
    private final List<ReservationView> reservations;
}
