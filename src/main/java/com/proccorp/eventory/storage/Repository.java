package com.proccorp.eventory.storage;

import java.util.List;

import com.proccorp.eventory.model.IndexedObject;

public interface Repository<T extends IndexedObject> {
    List<T> getAll();
    T get(String id);
    void delete(String id);
    void replace(String id, T element);
    T add(T element);
}