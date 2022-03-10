package com.gmail.radzkovevgeni.app.repository;

import java.sql.Connection;

public interface ConnectionRepository {
    Connection getConnection() throws ClassNotFoundException;
}
