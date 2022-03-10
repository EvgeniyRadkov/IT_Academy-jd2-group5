package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.repository.model.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends GeneralRepository<Users> {

    List<Users> findAllUsers(Connection connection) throws SQLException;
}
