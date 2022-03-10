package com.gmail.radzkovevgeni.app.repository;

import com.gmail.radzkovevgeni.app.repository.model.UserInformation;
import com.gmail.radzkovevgeni.app.repository.model.Users;

import java.sql.Connection;

public interface UserInformationRepository extends GeneralRepository<UserInformation> {

    UserInformation add(Connection connection, UserInformation userInformation, Users users);

}
