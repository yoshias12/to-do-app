package com.enigmacamp.controller;

import com.enigmacamp.config.HibernateUtil;
import com.enigmacamp.model.User;
import com.enigmacamp.service.UserService;
import com.enigmacamp.util.Helper;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserController {
    private UserService userService;
    private static User loggedInUser;
    Helper helper = new Helper();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public void login(){
        String username = helper.inputString("Username ");
        String password = helper.inputString("Password ");

        Optional<User> user = userService.login(username, password);
        if (user.isPresent()) {
            System.out.println("Login success");
            loggedInUser = user.get();
        } else {
            System.out.println("Login failed");
        }
    }

    public  void register() {
        String username = helper.inputString("Usename ");
        String password = helper.inputString("Password ");

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        userService.register(username, password);
        System.out.println("Berhasil didaftarkan...");
    }
}
