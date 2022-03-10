package com.gmail.radzkovevgeni.app.repository;

import java.sql.Connection;

public interface GeneralRepository<T> {

    T add(Connection connection, T t);
}
