package com.proccorp.eventory.date;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public interface TimeProvider {
    ZonedDateTime zonedNow();
    LocalDateTime localNow();
}