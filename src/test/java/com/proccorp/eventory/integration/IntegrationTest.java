package com.proccorp.eventory.integration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.Gson;
import com.proccorp.eventory.Application;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        Application.class }, properties = "server.port=8080", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Test
    public void actuator() {
        given()
                .baseUri("http://localhost:8080")
                .when()
                .get("/actuator/health")
                .then()
                .statusCode(200)
                .body(is("{\"status\":\"UP\"}"));
    }

    @Test
    public void listSchedules() {
        Response response = when()
                .get("http://localhost:8080" + "/schedules/");
        //        .then()
        //                .statusCode(200);
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void addSchedule() {
        Schedule requestedSchedule = new Schedule(null, 3,
                new User("Krzysiek", "123-987-789", null), "krk", "granie na teatralnym");
        String json = new Gson().toJson(requestedSchedule);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://localhost:8080" + "/schedules");

        Schedule returnedSchedule = response.getBody().as(Schedule.class);
        String scheduleId = returnedSchedule.getId();
        System.out.println(returnedSchedule);

        String path = "http://localhost:8080" + "/schedules/" + scheduleId;
        System.out.println(path);
        when()
                .get(path)
                .then()
                .statusCode(200);
    }

}
