package com.gmail.radzkovevgeni.app.controller;

import com.gmail.radzkovevgeni.app.repository.UserRepository;
import com.gmail.radzkovevgeni.app.repository.impl.UserRepositoryImpl;
import com.gmail.radzkovevgeni.app.service.DeleteAndCreateTableService;
import com.gmail.radzkovevgeni.app.service.UserService;
import com.gmail.radzkovevgeni.app.service.impl.DeleteAndCreateTableServiceImpl;
import com.gmail.radzkovevgeni.app.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "informationAboutUsersServlet", urlPatterns = {"/informationServlet"})
public class InformationAboutUsersServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InformationAboutUsersServlet.class);
    private static final DeleteAndCreateTableService beginApp = DeleteAndCreateTableServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        beginApp.createTables();
        logger.info("App launched");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userService.getAllUsers();

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<html><body>");
            writer.println("<h1> </h1>");
            writer.println("</body><//html>");
        }
        super.doGet(req, resp);
    }
}
