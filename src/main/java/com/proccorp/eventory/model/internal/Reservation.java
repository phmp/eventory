package com.proccorp.eventory.model.internal;

import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
public class Reservation extends IndexedObject {
    private final String additionalNotes;
    private final boolean confirmedByHost;
    private final ZonedDateTime subscriptionDateTime;
    private final User person;

    public Reservation(String additionalNotes, boolean confirmedByHost, ZonedDateTime subscriptionDateTime,
            User person) {
        super();
        this.additionalNotes = additionalNotes;
        this.confirmedByHost = confirmedByHost;
        this.subscriptionDateTime = subscriptionDateTime;
        this.person = person;
    }
}
