package com.gmail.radzkovevgeni.app.repository;

import java.sql.Connection;

public interface DatabaseRepository {
    void deleteAndCreateTable(Connection connection, String sqlCommand);
}
