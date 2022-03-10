package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.RoleRepository;
import com.gmail.radzkovevgeni.app.repository.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static com.gmail.radzkovevgeni.app.repository.RepositoryConstants.INSERT_INTO_ROLE;

public class RoleRepositoryImpl implements RoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    private static RoleRepository instance;

    private RoleRepositoryImpl() {
    }

    public static RoleRepository getInstance() {
        if (instance == null) {
            instance = new RoleRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Role add(Connection connection, Role role) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ROLE,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            String name = role.getName();
            preparedStatement.setString(1, name);
            String description = role.getDescription();
            preparedStatement.setString(2, description);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert role failed, no rows affected");
            }
            logger.debug("Affected rows when inserting in a table: {} ", affectedRows);
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Insert role failed, no ID obtained!");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return role;
    }
}
