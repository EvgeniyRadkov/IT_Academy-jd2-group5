package com.gmail.radzkovevgeni.repository.impl;

import com.gmail.radzkovevgeni.repository.model.Document;
import com.gmail.radzkovevgeni.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class DocumentRepositoryImpl implements DocumentRepository {

    @Override
    public Document add(Connection connection, Document document) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO document(unique_number, description) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
            Integer uniqueNumber = document.getUniqueNumber();
            preparedStatement.setObject(1, uniqueNumber);
            String description = document.getDescription();
            preparedStatement.setString(2, description);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert document failed, no rows field!");
            }
            log.debug("Affected ros when insert in a table", affectedRows);
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to insert document, identifier not received!");
                }
            }
        }
        return document;
    }

    @Override
    public List<Document> findAllById(Connection connection, int id) throws SQLException {
        List<Document> documents = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document where id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (!resultSet.next()) {
                    Document document = getDocumentFromDatabase(resultSet);
                    documents.add(document);
                }else {
                   throw new IllegalAccessError("Given ID does not exist!");
               }
            }
            return documents;
        }
    }

    @Override
    public int deleteById(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM document WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("No rows for delete!");
            }
        return rows;
        }
    }

    @Override
    public Document update(Connection connection, Document document) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE document SET description = ?")) {
            String description = document.getDescription();
            preparedStatement.setString(1, description);
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Invalid description");
            }
            return document;
        }
    }

    private Document getDocumentFromDatabase(ResultSet resultSet) throws SQLException {
        return Document.builder()
                .id(resultSet.getInt("id"))
                .uniqueNumber(resultSet.getInt("unique_number"))
                .description(resultSet.getString("description"))
                .build();
    }
}
