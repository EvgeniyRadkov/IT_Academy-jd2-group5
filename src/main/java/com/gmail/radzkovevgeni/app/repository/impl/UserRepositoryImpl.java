package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.UserRepository;
import com.gmail.radzkovevgeni.app.repository.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private static final String INSERT_INTO_USERS = "INSERT INTO users(username, password, is_active, age) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_IN_TABLES = "SELECT u.id, u.username, u.password, u.isActive, u.age, us.address, us.telephone FROM users u INNER JOIN user_information us ON u.id=us.user_id";

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
    public Users add(Connection connection, Users users) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                INSERT_INTO_USERS,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            String username = users.getUsername();
            preparedStatement.setString(1, username);
            String password = users.getPassword();
            preparedStatement.setString(2, password);
            Boolean isActive = users.getActive();
            preparedStatement.setBoolean(3, isActive);
            Integer age = users.getAge();
            preparedStatement.setInt(4, age);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inset users failed, no rows affected");
            }
            logger.debug("Affected rows when inserting in a table: {}", affectedRows);
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    users.setId(resultSet.getInt(1));
                } else {
                    throw new SQLException("Insert users failed, no ID obtained!");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public List<Users> findAllUsers(Connection connection) throws SQLException {
        String sql = SELECT_ALL_IN_TABLES;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            List<Users> users = new ArrayList<>();
            while (resultSet.next()) {
                Users user = gerUsersFromDatabase(resultSet);
                users.add(user);
            }
            return users;
        }
    }

    private Users gerUsersFromDatabase(ResultSet resultSet) throws SQLException {
        Users users = new Users();
        String id = resultSet.getString("id");
        users.setId(Integer.valueOf(id));
        String username = resultSet.getString("username");
        users.setUsername(username);
        String password = resultSet.getString("password");
        users.setPassword(password);
        String isActive = resultSet.getString("is_active");
        users.setActive(Boolean.valueOf(isActive));
        String age = resultSet.getString("age");
        users.setAge(Integer.valueOf(age));
        return users;
    }
}
