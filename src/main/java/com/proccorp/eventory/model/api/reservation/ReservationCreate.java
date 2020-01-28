package com.proccorp.eventory.model.api.reservation;

import lombok.Data;

@Data
public class ReservationCreate {
    private final String userId;
    private final String scheduleId;
    private final String eventId;
}
