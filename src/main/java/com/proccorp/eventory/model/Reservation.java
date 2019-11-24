package com.proccorp.eventory.model;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Reservation {
    private final boolean confirmedByHost;
    private final ZonedDateTime confirmationDateTime;
    private final User person;
}
