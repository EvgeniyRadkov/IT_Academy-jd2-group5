package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.DatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepositoryImpl implements DatabaseRepository {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImpl.class);

    private static DatabaseRepository instance;

    private DatabaseRepositoryImpl() {
    }

    public static DatabaseRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void deleteAndCreateTable(Connection connection, String sqlCommand) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
