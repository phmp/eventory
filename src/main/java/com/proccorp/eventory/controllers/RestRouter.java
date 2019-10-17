package com.proccorp.eventory.controllers;

import com.proccorp.eventory.model.Account;
import com.proccorp.eventory.model.ErrorResponse;
import com.proccorp.eventory.model.TransferResponse;
import com.proccorp.eventory.storage.AccountsRepository;
import com.proccorp.eventory.transfer.TransferManager;
import com.google.gson.Gson;
import com.google.inject.Inject;
import spark.Request;
import spark.Response;

import java.math.BigInteger;
import java.util.Collection;

public class RestRouter {

    private TransferManager manager;
    private AccountsRepository repository;
    private Gson gson = new Gson();

    @Inject
    public RestRouter(TransferManager manager, AccountsRepository repository) {
        this.manager = manager;
        this.repository = repository;
    }

    public Collection<Account> listAccountRoute(Request req, Response res) {
        res.type("application/json");
        res.status(200);
        return repository.getAll();
    }

    public Account getAccountRoute(Request req, Response res) {
        res.type("application/json");
        res.status(200);
        String id = req.params(":id");
        return repository.get(id);
    }

    public Account createAccountRoute(Request req, Response res) {
        res.type("application/json");
        String id = req.params(":id");
        String amount = req.params(":amount");
        repository.add(id, new BigInteger(amount));
        res.status(201);
        return repository.get(id);
    }

    public TransferResponse transferRoute(Request req, Response res) {
        res.type("application/json");
        res.status(200);
        String from = req.params(":from");
        String to = req.params(":to");
        String amount = req.params(":amount");
        manager.transfer(from, to, new BigInteger(amount));
        return TransferResponse.successfulTransfer();
    }

    public void wrongIdRoute(Exception exception, Request request, Response response) {
        response.status(400);
        response.type("application/json");
        response.body(gson.toJson(new ErrorResponse(exception.getMessage())));
    }

    public void transferErrorRoute(Exception exception, Request request, Response response) {
        response.status(500);
        response.type("application/json");
        response.body(gson.toJson(TransferResponse.failedTransfer(exception.getMessage())));
    }
}
