package com.proccorp.eventory.integration;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.proccorp.eventory.Application;
import com.proccorp.eventory.model.api.events.EventCreate;
import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.schedules.ScheduleView;
import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.api.users.UserView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = {Application.class}, properties = "server.port=8080", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest extends DefinedOperations {


    @Test
    public void actuator() {
        checkActuatorStatus();
    }

    @Test
    @Order(1)
    public void addRandomUsers() {
        UserCreate pawel = new UserCreate("pawel", "516...", "po 19 nie odbieram");
        UserCreate tomek = new UserCreate("tomek", "123098123", null);
        UserCreate lukasz = new UserCreate("lukasz K", "999", "spoznie sie");
        UserCreate renia = new UserCreate("Renia", "603 795 111", null);
        UserCreate andrzej = new UserCreate("andrzej", "111-2222-33", null);
        UserCreate ala = new UserCreate("Ala", "+48 22 123 32 22", "tylko SMS");

        List.of(pawel, tomek, lukasz, renia, andrzej, ala)
                .forEach(this::createUser);
        listAllUsers();
    }

    @Test
    public void listSchedules() {
        listAllSchedules();
    }

    @Test
    public void addSchedule() {
        UserCreate krzysiek = new UserCreate("Krzysiek", "123-987-789", "pisac sms, nie dzwonic :)");
        UserView user = createUser(krzysiek);
        ScheduleView schedule = createSchedule(user);
        viewSchedule(schedule.getId());
    }

    @Test
    public void createUser() {
        UserCreate pawel = new UserCreate("pawel", "516...", "po 19 nie odbieram");
        UserView user = createUser(pawel);
        viewUser(user.getId());
    }

    @Test
    public void createEventTest() {
        addSchedule();
        List<ScheduleView> scheduleViews = listAllSchedules();
        ScheduleView scheduleView = scheduleViews.stream().findFirst().orElseThrow();
        String scheduleViewId = scheduleView.getId();
//        String zonedDateTime = ZonedDateTime.now().plusDays(1).toString();
        String zonedDateTime = "2018-09-16T08:00:00+00:00[Europe/London]";
        EventCreate eventCreate = new EventCreate(zonedDateTime);
        EventView eventView = createEvent(scheduleViewId, eventCreate);
        getEvent(scheduleViewId, eventView.getId());
        viewSchedule(scheduleViewId);
    }

}