package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.ConnectionRepository;
import com.gmail.radzkovevgeni.app.repository.PropertiesRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionRepositoryImpl implements ConnectionRepository {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionRepositoryImpl.class);
    private static final String URL = "datasource.url";
    private static final String USERNAME = "datasource.username";
    private static final String PASSWORD = "datasource.password";

    private static ConnectionRepository instance;
    private PropertiesRepository propertiesRepository = PropertiesRepositoryImpl.getInstance();

    private HikariDataSource dataSource;

    private ConnectionRepositoryImpl() {
    }

    public static ConnectionRepository getInstance() {
        if (instance == null) {
            instance = new ConnectionRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException {
        if (dataSource == null) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(propertiesRepository.getProperty(URL));
            hikariConfig.setUsername(propertiesRepository.getProperty(USERNAME));
            hikariConfig.setPassword(propertiesRepository.getProperty(PASSWORD));
            Class.forName("org.postgresql.Driver");
            this.dataSource = new HikariDataSource(hikariConfig);
        }
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException();
        }
    }
}
