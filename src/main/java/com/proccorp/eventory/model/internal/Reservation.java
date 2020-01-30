package com.proccorp.eventory.model.internal;

import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
public class Reservation extends IndexedObject {
    private final String additionalNotes;
    private final String status;
    private final ZonedDateTime subscriptionDateTime;
    private final User person;
    private final Event event;

    public Reservation(String id, String additionalNotes, String status, ZonedDateTime subscriptionDateTime,
            User person, Event event) {
        super(id);
        this.additionalNotes = additionalNotes;
        this.status = status;
        this.subscriptionDateTime = subscriptionDateTime;
        this.person = person;
        this.event = event;
    }

    public Reservation(String additionalNotes, String status, ZonedDateTime subscriptionDateTime,
            User person, Event event) {
        super();
        this.additionalNotes = additionalNotes;
        this.status = status;
        this.subscriptionDateTime = subscriptionDateTime;
        this.person = person;
        this.event = event;
    }
}
