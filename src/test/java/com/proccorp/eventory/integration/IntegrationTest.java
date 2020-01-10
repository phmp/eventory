package com.proccorp.eventory.integration;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IntegrationTest{

    @Test
    public void actuator(){
        given()
                .baseUri("http://localhost:8080")
        .when()
                .get("/actuator/health")
        .then()
                .statusCode(200)
                .body(is("{\"status\":\"UP\"}"));
    }

    @Test
    public void listSchedules(){

        when()
                .get("http://localhost:8080"+"/schedules/")
                .then()
                .statusCode(200);
    }

    @Test
    public void listEvents(){

        when()
                .get("http://localhost:8080"+"/schedules/1/events/")
                .then()
                .statusCode(200);
    }

}
