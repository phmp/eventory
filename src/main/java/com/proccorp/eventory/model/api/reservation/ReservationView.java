package com.proccorp.eventory.model.api.reservation;

import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.users.UserView;

import lombok.Data;

@Data
public class ReservationView {
    private final String id;
    private final String status;
    private final UserView user;
    private final EventView event;
}
