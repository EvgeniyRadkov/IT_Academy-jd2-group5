package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.repository.model.Role;
import com.gmail.radzkovevgeni.app.repository.model.User;

import java.sql.Connection;

public interface UserRepository {
    User add(Connection connection, User users, Role role);
}
