package com.proccorp.eventory.model.api.events;

import com.proccorp.eventory.model.internal.Reservation;
import lombok.Data;

import java.util.List;

@Data
public class EventView {
    private String id;
    private String zonedDateTime;
    private List<Reservation> reservations;
}
