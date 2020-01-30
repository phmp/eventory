package com.proccorp.eventory.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proccorp.eventory.model.UserMapper;
import com.proccorp.eventory.model.api.schedules.ScheduleCreate;
import com.proccorp.eventory.model.api.users.UserCreate;
import com.proccorp.eventory.model.api.users.UserView;
import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.storage.user.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserController(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<UserView> getUser(){
        List<User> all = userRepository.getAll();
        List<UserView> userViews = all.stream()
                .map(userMapper::toView)
                .collect(Collectors.toList());
        return userViews;
    }

    @GetMapping("/{id}")
    public UserView getUser(@PathVariable String id){
        User user = userRepository.get(id);
        UserView userView = userMapper.toView(user);
        return userView;
    }

    @PostMapping
    public UserView createUser(@RequestBody UserCreate body){
        User user = userMapper.toInternal(body);
        User addedUser = userRepository.add(user);
        return userMapper.toView(addedUser);
    }

}
