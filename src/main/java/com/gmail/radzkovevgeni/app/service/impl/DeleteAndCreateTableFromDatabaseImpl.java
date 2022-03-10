package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.ConnectionRepository;
import com.gmail.radzkovevgeni.app.repository.DatabaseRepository;
import com.gmail.radzkovevgeni.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.DatabaseRepositoryImpl;
import com.gmail.radzkovevgeni.app.service.DeleteAndCreateTableFromDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DeleteAndCreateTableFromDatabaseImpl implements DeleteAndCreateTableFromDatabase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteAndCreateTableFromDatabaseImpl.class);
    private static final ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private static final DatabaseRepository databaseRepository = DatabaseRepositoryImpl.getInstance();
    private static final String DROP_TABLE_USERS = "drop table if exists users";
    private static final String DROP_TABLE_ROLE = "drop table if exists role";
    private static final String CREATE_TABLE_ROLE = "create table role(id serial primary key,name varchar(40),description text)";
    private static final String CREATE_TABLE_USERS = "create table users(id serial primary key,role_id int,username varchar(40),password varchar(7),create_by date,foreign key(role_id) references role(id))";

    private static DeleteAndCreateTableFromDatabase instance;

    private DeleteAndCreateTableFromDatabaseImpl() {
    }

    public static DeleteAndCreateTableFromDatabase getInstance() {
        if (instance == null) {
            instance = new DeleteAndCreateTableFromDatabaseImpl();
        }
        return instance;
    }

    @Override
    public void deleteAndCreateTable() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                databaseRepository.deleteAndCreateTable(connection, DROP_TABLE_USERS);
                databaseRepository.deleteAndCreateTable(connection, DROP_TABLE_ROLE);
                databaseRepository.deleteAndCreateTable(connection, CREATE_TABLE_ROLE);
                databaseRepository.deleteAndCreateTable(connection, CREATE_TABLE_USERS);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
            connection.commit();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
