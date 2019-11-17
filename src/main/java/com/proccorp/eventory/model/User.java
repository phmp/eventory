package com.proccorp.eventory.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final String name;
    private final String phoneNumber;
    private final String notes;
}
