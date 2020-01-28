package com.proccorp.eventory.model.api.users;

import lombok.Data;

@Data
public class UserView {
    private String id;
    private String name;
    private String phoneNumber;
    private String notes;
}
