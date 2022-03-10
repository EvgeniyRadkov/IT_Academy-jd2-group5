package com.gmail.radzkovevgeni.app.service;

import com.gmail.radzkovevgeni.app.repository.model.Role;

public interface CreateRoleService {

    Role createAdminRole();

    Role createUserRole();
}
