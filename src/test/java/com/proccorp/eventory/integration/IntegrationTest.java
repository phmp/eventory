package com.proccorp.eventory.integration;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class IntegrationTest extends ApplicationRunner {

    @Test
    public void actuator(){
        when()
                .get("/actuator")
        .then()
                .statusCode(200)
                .body(is("alive!"));
    }


    @Test
    public void listSchedules(){

        when()
                .get("/schedules")
                .then()
                .statusCode(200);
    }

    @Test
    public void listEvents(){

        when()
                .get("/schedules/1/events")
                .then()
                .statusCode(200);
    }

}
