package com.proccorp.eventory.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class IntegrationTest{

    @BeforeAll
    public static void initConfiguration() {
    }

    @Test
    public void actuator(){
        when()
                .get("http://localhost:8080"+"/actuator")
        .then()
                .statusCode(200)
                .body(contains("alive!"));
    }


    @Test
    public void listSchedules(){

        when()
                .get("http://localhost:8080"+"/schedules")
                .then()
                .statusCode(200);
    }

    @Test
    public void listEvents(){

        when()
                .get("http://localhost:8080"+"/schedules/1/events")
                .then()
                .statusCode(200);
    }

}
