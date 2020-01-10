package com.proccorp.eventory.date;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

@Service
public class SimpleTimeProvider implements TimeProvider {

    @Override public ZonedDateTime zonedNow() {
        return ZonedDateTime.now();
    }

    @Override public LocalDateTime localNow() {
        return LocalDateTime.now();
    }
}
