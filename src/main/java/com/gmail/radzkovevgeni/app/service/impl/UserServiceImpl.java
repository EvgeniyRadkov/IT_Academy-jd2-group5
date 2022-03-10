package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.ConnectionRepository;
import com.gmail.radzkovevgeni.app.repository.UserInformationRepository;
import com.gmail.radzkovevgeni.app.repository.UserRepository;
import com.gmail.radzkovevgeni.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.UserInformationRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.UserRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.model.UserInformation;
import com.gmail.radzkovevgeni.app.repository.model.Users;
import com.gmail.radzkovevgeni.app.service.UserService;
import com.gmail.radzkovevgeni.app.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private static final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private static final UserInformationRepository userInformationRepository = UserInformationRepositoryImpl.getInstance();

    private static UserService instance;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public UserDTO add(UserDTO addUserDTO) {
        Users user = convertUserDtoToUser(addUserDTO);
        UserInformation userInformation = convertUserDtoToUserInformation(addUserDTO, user);
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Users newUsers = userRepository.add(connection, user);
                UserInformation newUserInformation = userInformationRepository.add(connection, userInformation, user);
                UserDTO newAddUserDTO = convertToUserDTO(newUsers, newUserInformation);
                connection.commit();
                return newAddUserDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void getAllUsers() {
        try (Connection connection = connectionRepository.getConnection()) {
            List<Users> allUsers = userRepository.findAllUsers(connection);
            for (int i = 0; i < allUsers.size(); i++) {
                logger.info(String.valueOf(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private UserDTO convertToUserDTO(Users newUsers, UserInformation newUserInformation) {
        UserDTO addUserDTO = new UserDTO();
        addUserDTO.setId(newUsers.getId());
        addUserDTO.setUsername(newUsers.getUsername());
        addUserDTO.setPassword(newUsers.getPassword());
        addUserDTO.setActive(newUsers.getActive());
        addUserDTO.setAge(newUsers.getAge());
        addUserDTO.setUserId(newUserInformation.getUserId());
        addUserDTO.setAddress(newUserInformation.getAddress());
        addUserDTO.setTelephone(newUserInformation.getTelephone());
        return addUserDTO;
    }

    private Users convertUserDtoToUser(UserDTO addUserDTO) {
        Users users = new Users();
        users.setId(addUserDTO.getId());
        users.setUsername(addUserDTO.getUsername());
        users.setPassword(addUserDTO.getPassword());
        users.setActive(addUserDTO.getActive());
        users.setAge(addUserDTO.getAge());
        return users;
    }

    private UserInformation convertUserDtoToUserInformation(UserDTO addUserDTO, Users user) {
        UserInformation userInformation = new UserInformation();
        userInformation.setUserId(user.getId());
        userInformation.setAddress(addUserDTO.getAddress());
        userInformation.setTelephone(addUserDTO.getPassword());
        return userInformation;
    }
}
