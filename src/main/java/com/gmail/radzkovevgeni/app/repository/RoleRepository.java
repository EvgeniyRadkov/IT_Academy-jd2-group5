package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.repository.model.Role;

import java.sql.Connection;

public interface RoleRepository {
    Role add(Connection connection, Role role);
}
