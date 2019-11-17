package com.proccorp.eventory.app.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.proccorp.eventory.date.SimpleTimeProvider;
import com.proccorp.eventory.date.TimeProvider;
import com.proccorp.eventory.storage.SchedulesRepository;
import com.proccorp.eventory.storage.SchedulesRepositoryInMemory;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TimeProvider.class).to(SimpleTimeProvider.class);
    }

    @Provides
    @Singleton
    public SchedulesRepository createRepository() {
        return new SchedulesRepositoryInMemory();
    }

}
