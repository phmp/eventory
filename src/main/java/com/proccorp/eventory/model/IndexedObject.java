package com.proccorp.eventory.model;

import java.util.UUID;

public class IndexedObject {
    protected final String id;

    public String getId() {
        return id;
    }

    public IndexedObject() {
        this.id = UUID.randomUUID().toString();
    }
}
