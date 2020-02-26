package com.proccorp.eventory.integration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.proccorp.eventory.model.api.events.EventCreate;
import com.proccorp.eventory.model.api.events.EventView;
import com.proccorp.eventory.model.api.schedules.ScheduleCreate;
import com.proccorp.eventory.model.api.schedules.ScheduleView;
import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.api.users.UserView;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefinedOperations {
    protected static final Gson GSON = new Gson();
    private static final String HOST = "http://localhost:8080";
//    private static final String HOST = "http://eventory.proccorp.pl:8080";

    protected UserView createUser(UserCreate userCreate) {
        String json = GSON.toJson(userCreate);
        log.info("User to be added:\n"+ json);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(HOST + "/users");
        log.info("user added: " + response.getBody().prettyPrint());
        return response.as(UserView.class);
    }

    protected void viewUser(String userId) {
        String path = HOST + "/users/" + userId;
        log.info(path);
        Response response1 = when()
                .get(path);
        log.info("user created: " + response1.prettyPrint());
    }

    protected List<ScheduleView> listAllSchedules() {
        Response response = when()
                .get(HOST + "/schedules/");
        response.then().statusCode(200);
        log.info("actual schedules: " + response.getBody().prettyPrint());
        String s = response.asString();
        ScheduleView[] scheduleViews = GSON.fromJson(s, ScheduleView[].class);
        return List.copyOf(Arrays.asList(scheduleViews));
    }

    protected ScheduleView viewSchedule(String scheduleId) {
        String path = HOST + "/schedules/" + scheduleId;
        log.info(path);
        Response response = when()
                .get(path);
        String scheduleView = response.prettyPrint();
        log.info("retrieved schedule: " + scheduleView);
        return response.as(ScheduleView.class);
    }

    protected ScheduleView createSchedule(UserView user) {
        ScheduleCreate requestedSchedule = new ScheduleCreate(12, user.getId(), "krakow na Dywizjonu 303",
                "gramy bez spiny, poziom rekreacyjny");
        String json = GSON.toJson(requestedSchedule);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(HOST + "/schedules");

        String bodyresponsestring = response.getBody().prettyPrint();
        log.info("returned added schedule view:\n" + bodyresponsestring);

        ScheduleView returnedSchedule = response.getBody().as(ScheduleView.class);
        log.info("Returned schedule: " + returnedSchedule);
        return returnedSchedule;
    }

    protected void listAllUsers() {
        String path = HOST + "/users/";
        log.info(path);
        Response response1 = when()
                .get(path);
        log.info("users created: " + response1.prettyPrint());
    }

    protected void checkActuatorStatus() {
        given()
                .baseUri(HOST)
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(200)
                .body(is("{\"status\":\"UP\"}"));
    }

    protected EventView createEvent(String scheduleId, EventCreate eventCreate) {
        String json = GSON.toJson(eventCreate);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post(HOST + "/schedules/" + scheduleId + "/events");
        log.info("event adding response: " + response.getBody().prettyPrint());
        return response.as(EventView.class);
    }


    protected EventView getEvent(String scheduleId, String eventId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(HOST + "/schedules/" + scheduleId + "/events/" + eventId);
        log.info("get event:\n"+response.getBody().asString());
        return response.as(EventView.class);
    }
}