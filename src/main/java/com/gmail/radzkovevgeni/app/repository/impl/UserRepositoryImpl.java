package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.UserRepository;
import com.gmail.radzkovevgeni.app.repository.model.Role;
import com.gmail.radzkovevgeni.app.repository.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

import static com.gmail.radzkovevgeni.app.repository.RepositoryConstants.INSERT_INTO_USERS;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User add(Connection connection, User user, Role role) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS, Statement.RETURN_GENERATED_KEYS)
        ) {

            Integer id = role.getId();
            preparedStatement.setInt(1, id);
            String username = user.getUsername();
            preparedStatement.setString(2, username);
            String password = user.getPassword();
            preparedStatement.setString(3, password);
            LocalDate createBy = user.getCreateBy();
            preparedStatement.setObject(4, createBy);

            int executeUpdate = preparedStatement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("Rows not added to table");
            }
            logger.debug("Affected rows when inserting in a table: {} ", executeUpdate);
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Insert users failed, no ID!");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
