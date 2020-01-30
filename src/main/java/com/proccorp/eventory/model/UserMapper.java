package com.proccorp.eventory.model;

import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.api.users.UserView;
import com.proccorp.eventory.model.internal.User;

@Component
public class UserMapper {

    public UserView toView(User user) {
        return new UserView(user.getId(), user.getName(), user.getPhoneNumber(), user.getNotes());
    }

    public User toInternal(UserCreate body) {
        return new User(body.getName(), body.getPhoneNumber(), body.getNotes());
    }
}
