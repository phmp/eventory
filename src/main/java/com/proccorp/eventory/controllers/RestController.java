package com.proccorp.eventory.controllers;

import com.proccorp.eventory.storage.IncorrectRequestedDataException;
import com.proccorp.eventory.transfer.TransferFailureException;
import com.google.gson.Gson;
import com.google.inject.Inject;

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
        get("/schedules/1/events/1/", this::eventInfo);
        post("/schedules/1/events/1/addMe", this::subscribe);

        // OlD PATHS - I leave it here just to easy access for checking things
        oldPaths();
    }

    private Object subscribe(Request request, Response response) {
        return "you have been added successfully";
    }

    private String eventInfo(Request request, Response response) {
        return "Simple event that occurs every monday at 19:00 in ZSO nr 14";
    }

    private void oldPaths() {
        path("/accounts", () -> {
            get("", router::listAccountRoute, gson::toJson);
            get("/", router::listAccountRoute, gson::toJson);
            get("/:id", router::getAccountRoute, gson::toJson);
            get("/:id/new/:amount", router::createAccountRoute, gson::toJson);
            get("/:from/transfer/:to/:amount", router::transferRoute, gson::toJson);
        });
        exception(IncorrectRequestedDataException.class, router::wrongIdRoute);
        exception(TransferFailureException.class, router::transferErrorRoute);
    }
}
