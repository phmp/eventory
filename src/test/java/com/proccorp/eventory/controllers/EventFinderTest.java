package com.proccorp.eventory.controllers;

import static org.testng.Assert.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.testng.annotations.Test;

import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Schedule;

public class EventFinderTest {

    @Test
    public void test(){
        ZonedDateTime currentDateTime = ZonedDateTime.of(2020,1,10,15,30,0,0, ZoneId.systemDefault());
        ZonedDateTime eventDateTime = currentDateTime.plusHours(2);
        new Event(new Schedule(), eventDateTime, List.of());
    }

}