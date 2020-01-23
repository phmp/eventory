package com.proccorp.eventory.storage.user;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.proccorp.eventory.model.internal.Schedule;
import com.proccorp.eventory.model.internal.User;
import com.proccorp.eventory.storage.RepositoryInMemory;

@Component
@Profile("memory")
public class UsersRepositoryInMemory extends RepositoryInMemory<User> implements UserRepository {

}
