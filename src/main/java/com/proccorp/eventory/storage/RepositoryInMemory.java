package com.proccorp.eventory.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.proccorp.eventory.model.IndexedObject;

public class RepositoryInMemory<T extends IndexedObject> implements Repository<T> {
    private Map<String, T> elements = new HashMap<>();

    @Override public List<T> getAll() {
        return new ArrayList<>(elements.values());
    }

    @Override public T get(String id) {
        return elements.get(id);
    }

    @Override public void delete(String id) {
        elements.remove(id);
    }

    @Override public void replace(String id, T element) {
        elements.replace(id, element);
    }

    @Override public T add(T element) {
        elements.putIfAbsent(element.getId(), element);
        return element;
    }

}
