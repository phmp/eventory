package com.proccorp.eventory.storage;

import java.util.List;

public interface Repository<T> {
    List<T> getAll();
    T get(String id);
    void delete(String id);
    void replace(String id, T element);
}