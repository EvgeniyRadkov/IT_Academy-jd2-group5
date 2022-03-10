package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.ConnectionRepository;
import com.gmail.radzkovevgeni.app.repository.RoleRepository;
import com.gmail.radzkovevgeni.app.repository.UserRepository;
import com.gmail.radzkovevgeni.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.RoleRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.impl.UserRepositoryImpl;
import com.gmail.radzkovevgeni.app.repository.model.Role;
import com.gmail.radzkovevgeni.app.repository.model.User;
import com.gmail.radzkovevgeni.app.service.CreateRoleService;
import com.gmail.radzkovevgeni.app.service.CreateUserDTO;
import com.gmail.radzkovevgeni.app.service.CreateUserService;
import com.gmail.radzkovevgeni.app.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.radzkovevgeni.app.service.ServiceConstants.*;

public class CreateUserDTOImpl implements CreateUserDTO {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserDTOImpl.class);
    private static final ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();
    private static final RoleRepository roleRepository = RoleRepositoryImpl.getInstance();
    private static final UserRepository userRepository = UserRepositoryImpl.getInstance();

    private static CreateUserDTO instance;

    private CreateUserDTOImpl() {
    }

    public static CreateUserDTO getInstance() {
        if (instance == null) {
            instance = new CreateUserDTOImpl();
        }
        return instance;
    }

    @Override
    public List<UserDTO> add() {
        CreateRoleService createRoleService = CreateRoleServiceImpl.getInstance();
        Role admin = createRoleService.createAdminRole();
        Role user = createRoleService.createUserRole();

        LocalDate localDate = LocalDate.now();

        CreateUserService createUserService = CreateUserServiceImpl.getInstance();
        User userOne = createUserService.createUser(USER_ONE_NAME, USER_ONE_PASSWORD, localDate);
        User userTwo = createUserService.createUser(USER_TWO_NAME, USER_TWO_PASSWORD, localDate);

        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            Role addAdminRole = roleRepository.add(connection, admin);
            Role addUserRole = roleRepository.add(connection, user);
            List<User> users = new ArrayList<>();
            User addUserOne = userRepository.add(connection, userOne, addAdminRole);
            users.add(addUserOne);
            User addUserTwo = userRepository.add(connection, userTwo, addUserRole);
            users.add(addUserTwo);
            List<UserDTO> addedUserDTO = convertToDTO(users);
            connection.commit();
            return addedUserDTO;
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private List<UserDTO> convertToDTO(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setCreateBy(user.getCreateBy());
            userDTO.setRoleId(user.getRoleId());
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
}
