package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.ConnectionRepository;
import com.gmail.radzkovevgeni.app.repository.DeleteAndCreateTableRepository;
import com.gmail.radzkovevgeni.app.repository.PropertiesRepository;
import com.gmail.radzkovevgeni.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.DeleteAndCreateTableRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.PropertiesRepositoryImpl;
import com.gmail.radzkovevgeni.app.service.DeleteAndCreateTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static com.gmail.radzkovevgeni.app.service.TaskConstance.*;

public class DeleteAndCreateTableServiceImpl implements DeleteAndCreateTableService {

    private static final Logger logger = LoggerFactory.getLogger(DeleteAndCreateTableServiceImpl.class);
    private static final ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private static final PropertiesRepository property = PropertiesRepositoryImpl.getInstance();
    private static final DeleteAndCreateTableRepository deleteAndCreateTablesRepository = DeleteAndCreateTableRepositoryImpl.getInstance();

    private static DeleteAndCreateTableService instance;

    private DeleteAndCreateTableServiceImpl() {
    }

    public static DeleteAndCreateTableService getInstance() {
        if (instance == null) {
            instance = new DeleteAndCreateTableServiceImpl();
        }
        return instance;
    }

    @Override
    public void createTables() {
        try (Connection connection = connectionRepository.getConnection()) {
            deleteAndCreateTablesRepository.deleteAndCreateTable(connection, property.getProperty(DELETE_TABLE_USER_INFORMATION));
            deleteAndCreateTablesRepository.deleteAndCreateTable(connection, property.getProperty(DELETE_TABLE_USERS));
            deleteAndCreateTablesRepository.deleteAndCreateTable(connection, property.getProperty(CREATE_TABLE_USERS));
            deleteAndCreateTablesRepository.deleteAndCreateTable(connection, property.getProperty(CREATE_TABLE_USER_INFORMATION));
        } catch (SQLException | ClassNotFoundException e) {
            logger.debug("Delete and create tables finished");
            logger.error(e.getMessage(), e);
        }
    }
}
