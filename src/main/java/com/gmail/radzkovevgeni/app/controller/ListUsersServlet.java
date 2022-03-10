package com.gmail.radzkovevgeni.app.controller;

import com.gmail.radzkovevgeni.app.service.DeleteAndCreateTableService;
import com.gmail.radzkovevgeni.app.service.UserService;
import com.gmail.radzkovevgeni.app.service.impl.DeleteAndCreateTableServiceImpl;
import com.gmail.radzkovevgeni.app.service.impl.UserServiceImpl;
import com.gmail.radzkovevgeni.app.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "listUsersServlet", urlPatterns = {"/users"})
public class ListUsersServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ListUsersServlet.class);
    private static final DeleteAndCreateTableService beginApp = DeleteAndCreateTableServiceImpl.getInstance();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void init() throws ServletException {
        beginApp.createTables();
        logger.info("App launched");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO addUserDTO = new UserDTO();
        String username = req.getParameter("username");
        validateUsername(username);
        addUserDTO.setUsername(username);
        String password = req.getParameter("password");
        validatePassword(password);
        addUserDTO.setPassword(password);
        String isActive = req.getParameter("is_active");
        validateIsActive(isActive);
        addUserDTO.setActive(Boolean.valueOf(isActive));
        String age = req.getParameter("age");
        validateAge(age);
        addUserDTO.setAge(Integer.valueOf(age));
        String address = req.getParameter("address");
        validateAddress(address);
        addUserDTO.setAddress(address);
        String telephone = req.getParameter("telephone");
        validateTelephone(telephone);
        addUserDTO.setTelephone(telephone);

        UserDTO userDTO = userService.add(addUserDTO);

        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<html><body>");
            writer.println("<h1>" + userDTO + "</h1>");
            writer.println("</body></html>");
        }
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalStateException("Username does not have appropriate format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalStateException("Password does not have appropriate format");
        }
    }

    private void validateIsActive(String isActive) {
        if (isActive == null || isActive.isBlank()) {
            throw new IllegalStateException("Is active does not have appropriate format");
        }
    }

    private void validateAge(String age) {
        if (age == null || age.isBlank()) {
            throw new IllegalStateException("Age does not have appropriate format");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalStateException("Address does not have appropriate format");
        }
    }

    private void validateTelephone(String telephone) {
        if (telephone == null || telephone.isBlank()) {
            throw new IllegalStateException("Telephone does not have appropriate format");
        }
    }
}
