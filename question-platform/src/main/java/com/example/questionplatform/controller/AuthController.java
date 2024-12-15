package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.request.LoginReq;
import com.example.questionplatform.request.RegisterReq;
import com.example.questionplatform.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    Database database;

    @PostMapping("/register")
    public Response registerUser(@RequestBody RegisterReq registerReq) {
        if (! registerReq.getPassword().equals(registerReq.getConfirmPassword())) {
            return new ErrorRes("Password and confirm password do not match");
        }

        if(database.getUserByEmail(registerReq.getEmail()) != null) {
            return new ErrorRes("Email is already registered");
        }

        User user = database.registerUser(registerReq.getUsername(), registerReq.getEmail(), registerReq.getPassword(), registerReq.getAvatar_url(), registerReq.getRole());
        return new RegisterRes("User registered successfully", new UserRes(user.getId(), user.getUsername(), user.getEmail(), user.getAvatar_url(), user.getRole().toString(), user.getPoints()));
    }

    @PostMapping("/login")
    public Response loginUser(@RequestBody LoginReq loginReq){
        System.out.println("login req");
        User user = database.getUserByEmail(loginReq.getEmail());
        if (user == null) {
            return new ErrorRes("User not found");
        }

        if (! user.comparePassword(loginReq.getPassword())) {
            return new ErrorRes("Invalid email or password");
        }

        return new LoginRes("Login successful", database.loginUser(user), user);
    }
}
