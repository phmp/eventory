package com.proccorp.eventory.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

public class EventFinderTest {

    @Test
    public void test(){
        ZonedDateTime currentDateTime = ZonedDateTime.of(2020,1,10,15,30,0,0, ZoneId.systemDefault());
        ZonedDateTime eventDateTime = currentDateTime.plusHours(2);
//        new Event(new Schedule(), eventDateTime, List.of());
    }

}