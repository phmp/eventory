package com.proccorp.eventory.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

public class CreateNewSchedule {

    @Test
    public void test() {
        given()
                .baseUri("http://localhost:8080")
                .body("{}")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
        .when()
                .post("/schedules")
        .then()
                .statusCode(200);
    }

}
