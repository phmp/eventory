package com.proccorp.eventory.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.model.SubscriptionRequest;
import com.proccorp.eventory.storage.SchedulesRepository;

import spark.Request;
import spark.Response;

public class RestRouter {

    private Gson gson = new Gson();
    private final SchedulesRepository schedulesRepository;
    private final SchedulesController schedulesController;

    @Inject
    public RestRouter(SchedulesRepository schedulesRepository,
            SchedulesController schedulesController) {
        this.schedulesRepository = schedulesRepository;
        this.schedulesController = schedulesController;
    }

    public Event event(Request request, Response response) {
        String scheduleId = request.params("scheduleId");
        String eventId = request.params("eventId");
        return schedulesController.getCurrent(scheduleId);
    }

    public List<Schedule> schedules(Request request, Response response) {
        return schedulesRepository.getAll();
    }

    public Reservation addMe(Request request, Response response) {
        String body = request.body();
        SubscriptionRequest subscriptionRequest = gson.fromJson(body, SubscriptionRequest.class);

        return null;
    }

    //    public Collection<Account> listAccountRoute(Request req, Response res) {
    //        res.type("application/json");
    //        res.status(200);
    //        return repository.getAll();
    //    }
    //
    //    public Account getAccountRoute(Request req, Response res) {
    //        res.type("application/json");
    //        res.status(200);
    //        String id = req.params(":id");
    //        return repository.get(id);
    //    }
    //
    //    public Account createAccountRoute(Request req, Response res) {
    //        res.type("application/json");
    //        String id = req.params(":id");
    //        String amount = req.params(":amount");
    //        repository.add(id, new BigInteger(amount));
    //        res.status(201);
    //        return repository.get(id);
    //    }
    //
    //    public TransferResponse transferRoute(Request req, Response res) {
    //        res.type("application/json");
    //        res.status(200);
    //        String from = req.params(":from");
    //        String to = req.params(":to");
    //        String amount = req.params(":amount");
    //        manager.transfer(from, to, new BigInteger(amount));
    //        return TransferResponse.successfulTransfer();
    //    }

}
