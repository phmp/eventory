package com.proccorp.eventory.model.api.users;

import lombok.Data;

@Data
public class UserCreate {
    private final String name;
    private final String phoneNumber;
    private final String notes;
}
