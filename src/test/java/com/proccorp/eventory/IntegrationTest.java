package com.proccorp.eventory;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.proccorp.eventory.app.Application;

import io.restassured.RestAssured;
import spark.Spark;

public class IntegrationTest {

    @Test
    public void test(){
        when()
                .get("/actuator")
        .then()
                .statusCode(200)
                .body(is("Alive!"));
    }

    @BeforeClass
    public void startApp() {
        new Application().start();
        Spark.awaitInitialization();
    }

    @BeforeSuite
    public void initConfiguration() {
        RestAssured.baseURI = "http://localhost";
    }

}
