package com.proccorp.eventory.model.internal;

import java.util.Objects;
import java.util.UUID;

public class IndexedObject {
    protected final String id;

    public String getId() {
        return id;
    }

    public IndexedObject() {
        this.id = UUID.randomUUID().toString();
    }

    public IndexedObject(String id) {
        this.id = id;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        IndexedObject that = (IndexedObject) o;
        return Objects.equals(id, that.id);
    }

    @Override public int hashCode() {
        return Objects.hash(id);
    }
}
