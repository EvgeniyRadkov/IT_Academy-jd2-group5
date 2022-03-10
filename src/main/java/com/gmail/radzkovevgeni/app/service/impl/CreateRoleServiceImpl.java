package com.gmail.radzkovevgeni.app.service.impl;

import com.gmail.radzkovevgeni.app.repository.model.Role;
import com.gmail.radzkovevgeni.app.service.CreateRoleService;

import static com.gmail.radzkovevgeni.app.service.ServiceConstants.*;

public class CreateRoleServiceImpl implements CreateRoleService {

    private static CreateRoleService instance;

    private CreateRoleServiceImpl() {
    }

    public static CreateRoleService getInstance() {
        if (instance == null) {
            instance = new CreateRoleServiceImpl();
        }
        return instance;
    }

    @Override
    public Role createAdminRole() {
        Role adminRole = new Role();
        adminRole.setName(ROLE_NAME_ADMIN);
        adminRole.setDescription(ROLE_DESCRIPTION_ADMIN);
        return adminRole;
    }

    @Override
    public Role createUserRole() {
        Role userRole = new Role();
        userRole.setName(ROLE_NAME_USER);
        userRole.setDescription(ROLE_DESCRIPTION_USER);
        return userRole;
    }
}
