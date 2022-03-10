package com.gmail.radzkovevgeni.app.repository;

public interface RepositoryConstants {
    String CONFIG_PROPERTIES = "config.properties";
    String URL = "datasource.url";
    String USERNAME = "datasource.username";
    String PASSWORD = "datasource.password";
    String INSERT_INTO_ROLE = "INSERT INTO role(name, description) VALUES (?, ?)";
    String INSERT_INTO_USERS = "INSERT INTO users(role_id, username, password, create_by) VALUES (?, ?, ?, ?)";
}