package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.model.User;
import com.gmail.radzkovevgeni.app.service.CreateUserService;

import java.time.LocalDate;

public class CreateUserServiceImpl implements CreateUserService {

    private static CreateUserService instance;

    private CreateUserServiceImpl() {
    }

    public static CreateUserService getInstance() {
        if (instance == null) {
            instance = new CreateUserServiceImpl();
        }
        return instance;
    }

    @Override
    public User createUser(String username, String password, LocalDate createBy) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateBy(createBy);
        return user;
    }
}