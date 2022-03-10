package com.gmail.radzkovevgeni.app.repository;

import java.sql.Connection;

public interface DeleteAndCreateTableRepository {

    void deleteAndCreateTable(Connection connection, String name);



}
