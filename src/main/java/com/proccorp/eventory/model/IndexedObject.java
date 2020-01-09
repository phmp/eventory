package com.proccorp.eventory.model;

import java.util.UUID;

public class IndexedObject {
    protected final String id;

    public IndexedObject() {
        this.id = UUID.randomUUID().toString();
    }
}
