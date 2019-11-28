package com.proccorp.eventory.model;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Reservation {
    private final String id;
    private final String additionalNotes;
    private final boolean confirmedByHost;
    private final ZonedDateTime subscriptionDateTime;
    private final User person;
}
