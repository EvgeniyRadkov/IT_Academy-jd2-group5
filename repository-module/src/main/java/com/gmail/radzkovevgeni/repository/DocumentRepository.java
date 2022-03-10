package com.gmail.radzkovevgeni.repository;

import com.gmail.radzkovevgeni.repository.model.Document;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DocumentRepository {
    Document add(Connection connection, Document document) throws SQLException;

    List<Document> findAllById(Connection connection, int id) throws SQLException;

    int deleteById(Connection connection, int id) throws SQLException;

    Document update(Connection connection, Document document) throws SQLException;
}
