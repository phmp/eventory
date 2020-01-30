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
import com.proccorp.eventory.model.api.schedules.ScheduleCreate;
import com.proccorp.eventory.model.api.schedules.ScheduleView;
import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.api.users.UserView;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        Application.class }, properties = "server.port=8080", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    public static final Gson GSON = new Gson();

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
    public void createUser() {
        UserCreate pawel = new UserCreate("pawel", "516...", "po 19 nie odbieram");
        String json = GSON.toJson(pawel);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://localhost:8080" + "/users");
        System.out.println("user added: " + response.getBody().prettyPrint());

        String path = "http://localhost:8080" + "/users/" + response.as(UserView.class).getId();
        System.out.println(path);
        Response response1 = when()
                .get(path);
        System.out.println("user created: " + response1.prettyPrint());
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
        UserCreate krzysiek = new UserCreate("Krzysiek", "123-987-789", "pisac sms, nie dzwonic :)");
        String json1 = GSON.toJson(krzysiek);
        Response response1 = given()
                .contentType(ContentType.JSON)
                .body(json1)
                .when()
                .post("http://localhost:8080" + "/users");
        System.out.println("user added: " + response1.getBody().prettyPrint());
        String craetedUserId = response1.as(UserView.class).getId();
        ScheduleCreate requestedSchedule = new ScheduleCreate(12, craetedUserId, "krakow na Dywizjonu 303", "gramy bez spiny, poziom rekreacyjny");
        String json = GSON.toJson(requestedSchedule);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://localhost:8080" + "/schedules");

        String bodyresponsestring = response.getBody().prettyPrint();
        System.out.println("returned added schedule view:\n" + bodyresponsestring);
        ScheduleView returnedSchedule = response.getBody().as(ScheduleView.class);
        String scheduleId = returnedSchedule.getId();
        System.out.println(returnedSchedule);

        String path = "http://localhost:8080" + "/schedules/" + scheduleId;
        System.out.println(path);
        Response response2 = when()
                .get(path);
        String scheduleView = response2.prettyPrint();
        System.out.println("retrieved schedule: " + scheduleView);
    }
}
