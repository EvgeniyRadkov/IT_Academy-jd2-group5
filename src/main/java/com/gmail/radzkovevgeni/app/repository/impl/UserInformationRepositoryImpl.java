package com.gmail.radzkovevgeni.app.repository.impl;

import com.gmail.radzkovevgeni.app.repository.UserInformationRepository;
import com.gmail.radzkovevgeni.app.repository.model.UserInformation;
import com.gmail.radzkovevgeni.app.repository.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserInformationRepositoryImpl implements UserInformationRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserInformationRepositoryImpl.class);
    private static final String INSERT_INTO_USER_INFORMATION = "INSERT INTO user_information(user_id, address, telephone) VALUES (?, ?, ?)";

    private static UserInformationRepository instance;

    private UserInformationRepositoryImpl() {
    }

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInformation) {
        return null;
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInformation, Users users) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USER_INFORMATION,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            Integer id = users.getId();
            preparedStatement.setInt(1, id);
            String address = userInformation.getAddress();
            preparedStatement.setString(2, address);
            String telephone = userInformation.getTelephone();
            preparedStatement.setString(3, telephone);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inset user_information failed, no rows affected");
            }
            logger.debug("Affected rows when inserting in a table: {}", affectedRows);
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userInformation.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Insert user_information failed, no ID obtained!");
                }
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return userInformation;
    }
}
