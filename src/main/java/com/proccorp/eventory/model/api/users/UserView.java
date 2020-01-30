package com.proccorp.eventory.model.api.users;

import lombok.Data;

@Data
public class UserView {
    private final String id;
    private final String name;
    private final String phoneNumber;
    private final String notes;
}
