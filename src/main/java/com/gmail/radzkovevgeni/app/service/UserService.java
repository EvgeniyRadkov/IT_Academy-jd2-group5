package com.gmail.radzkovevgeni.app.service;

import com.gmail.radzkovevgeni.app.service.model.UserDTO;

public interface UserService {
    UserDTO add(UserDTO addUserDTO);

    void getAllUsers();
}
