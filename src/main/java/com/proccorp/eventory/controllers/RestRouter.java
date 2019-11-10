package com.proccorp.eventory.controllers;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.List;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.proccorp.eventory.model.ErrorResponse;
import com.proccorp.eventory.model.Event;
import com.proccorp.eventory.model.Reservation;
import com.proccorp.eventory.model.Schedule;
import com.proccorp.eventory.storage.SchedulesRepository;

import spark.Request;
import spark.Response;

public class RestRouter {

    private Gson gson = new Gson();
    private SchedulesRepository schedulesRepository;

    @Inject
    public RestRouter(SchedulesRepository schedulesRepository){
        this.schedulesRepository = schedulesRepository;
    }

    public Event event(Request request, Response response) {
        String scheduleId = request.params("scheduleId");
        String eventId = request.params("eventId");
        Schedule schedule = schedulesRepository.get(scheduleId);
        Event event = schedule.getEvents().stream().findFirst().orElseThrow();
        return event;
    }

    public List<Schedule> schedules(Request request, Response response) {
        return schedulesRepository.getAll();
    }

    public Reservation addMe(Request request, Response response) {
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
