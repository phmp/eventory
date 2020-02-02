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

    protected UserView createUser(UserCreate userCreate) {
        String json = GSON.toJson(userCreate);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://localhost:8080" + "/users");
        log.info("user added: " + response.getBody().prettyPrint());
        return response.as(UserView.class);
    }

    protected void viewUser(String userId) {
        String path = "http://localhost:8080" + "/users/" + userId;
        log.info(path);
        Response response1 = when()
                .get(path);
        log.info("user created: " + response1.prettyPrint());
    }

    protected List<ScheduleView> listAllSchedules() {
        Response response = when()
                .get("http://localhost:8080" + "/schedules/");
        response.then().statusCode(200);
        log.info("actual schedules: " + response.getBody().prettyPrint());
        String s = response.asString();
        ScheduleView[] scheduleViews = GSON.fromJson(s, ScheduleView[].class);
        return List.copyOf(Arrays.asList(scheduleViews));
    }

    protected void viewSchedue(String scheduleId) {
        String path = "http://localhost:8080" + "/schedules/" + scheduleId;
        log.info(path);
        Response response2 = when()
                .get(path);
        String scheduleView = response2.prettyPrint();
        log.info("retrieved schedule: " + scheduleView);
    }

    protected ScheduleView createSchedule(UserView user) {
        ScheduleCreate requestedSchedule = new ScheduleCreate(12, user.getId(), "krakow na Dywizjonu 303",
                "gramy bez spiny, poziom rekreacyjny");
        String json = GSON.toJson(requestedSchedule);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://localhost:8080" + "/schedules");

        String bodyresponsestring = response.getBody().prettyPrint();
        log.info("returned added schedule view:\n" + bodyresponsestring);

        ScheduleView returnedSchedule = response.getBody().as(ScheduleView.class);
        log.info("Returned schedule: " + returnedSchedule);
        return returnedSchedule;
    }

    protected void listAllUsers() {
        String path = "http://localhost:8080" + "/users/";
        log.info(path);
        Response response1 = when()
                .get(path);
        log.info("users created: " + response1.prettyPrint());
    }

    protected void checkActuatorStatus() {
        given()
                .baseUri("http://localhost:8080")
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
                .post("http://localhost:8080" + "/schedules/" + scheduleId + "/events");
        log.info("event adding response: " + response.getBody().prettyPrint());
        return response.as(EventView.class);
    }
}