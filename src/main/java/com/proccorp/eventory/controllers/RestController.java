package com.proccorp.eventory.controllers;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.proccorp.eventory.model.Person;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

public class RestController {

    private RestRouter router;
    private Gson gson = new Gson();

    @Inject
    public RestController(RestRouter router) {
        this.router = router;
    }

    public void setupEndpoints() {
        port(80);
        get("/actuator", ((request, response) -> "alive!"));
        get("/schedules", router::schedules);
        get("/schedules/", router::schedules);
        get("/schedules/:scheduleId/events/:eventId/", router::event);
        post("/schedules/:scheduleId/events/:eventId/addMe", router::addMe);
    }


}
