package com.proccorp.eventory.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Reservation {
    private boolean confirmedByHost;
    private ZonedDateTime confirmationDateTime;
    private Person person;
}
