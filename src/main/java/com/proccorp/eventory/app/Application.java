package com.proccorp.eventory.app;

import com.proccorp.eventory.app.configuration.ConfigurationModule;
import com.proccorp.eventory.controllers.RestController;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {

    Injector injector;

    public Application() {
        this.injector = Guice.createInjector(new ConfigurationModule());
    }

    public void start() {
        RestController restController = injector.getInstance(RestController.class);
        restController.setupEndpoints();
    }

}
