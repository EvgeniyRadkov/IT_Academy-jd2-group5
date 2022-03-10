package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.DeleteAndCreateTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteAndCreateTableRepositoryImpl implements DeleteAndCreateTableRepository {

    private static final Logger logger = LoggerFactory.getLogger(DeleteAndCreateTableRepositoryImpl.class);
    private static DeleteAndCreateTableRepository instance;

    private DeleteAndCreateTableRepositoryImpl() {
    }

    public static DeleteAndCreateTableRepository getInstance() {
        if (instance == null) {
            instance = new DeleteAndCreateTableRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void deleteAndCreateTable(Connection connection, String name) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(name);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
