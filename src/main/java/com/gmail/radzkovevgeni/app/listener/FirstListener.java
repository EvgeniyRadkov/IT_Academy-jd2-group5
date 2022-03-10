package com.gmail.radzkovevgeni.app.listener;

import com.gmail.radzkovevgeni.app.service.CreateUserDTO;
import com.gmail.radzkovevgeni.app.service.DeleteAndCreateTableFromDatabase;
import com.gmail.radzkovevgeni.app.service.impl.CreateUserDTOImpl;
import com.gmail.radzkovevgeni.app.service.impl.DeleteAndCreateTableFromDatabaseImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        DeleteAndCreateTableFromDatabase deleteAndCreateTableFromDatabase = DeleteAndCreateTableFromDatabaseImpl.getInstance();
        deleteAndCreateTableFromDatabase.deleteAndCreateTable();

        CreateUserDTO createUserDTO = CreateUserDTOImpl.getInstance();
        createUserDTO.add();
    }
}
