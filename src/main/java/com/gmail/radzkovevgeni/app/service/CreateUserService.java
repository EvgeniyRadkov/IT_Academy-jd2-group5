package com.gmail.radzkovevgeni.app.service;

import com.gmail.radzkovevgeni.app.repository.model.User;

import java.time.LocalDate;

public interface CreateUserService {
    User createUser(String username, String password, LocalDate createBy);
}