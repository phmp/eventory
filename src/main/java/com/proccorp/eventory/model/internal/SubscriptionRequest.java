package com.proccorp.eventory.model.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubscriptionRequest {
    private final User user;
    private final String scheduleId;
    private final String eventId;
}