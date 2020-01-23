package com.proccorp.eventory.model.internal;

import lombok.Data;

@Data
public class User extends IndexedObject {
    private String name;
    private String phoneNumber;
    private String notes;

    public User() {
        super();
    }

    public User(String id, String name, String phoneNumber, String notes) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public User(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }
}
