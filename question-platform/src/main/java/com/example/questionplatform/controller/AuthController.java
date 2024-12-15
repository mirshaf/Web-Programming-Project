package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.request.LoginReq;
import com.example.questionplatform.request.RegisterReq;
import com.example.questionplatform.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    Database database;

    @PostMapping("/register")
    public Response registerUser(@RequestBody RegisterReq registerReq) {
        System.out.println("register");
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
        System.out.println("login");
        User user = database.getUserByEmail(loginReq.getEmail());
        if (user == null) {
            return new ErrorRes("User not found");
        }

        if (! user.comparePassword(loginReq.getPassword())) {
            return new ErrorRes("Invalid email or password");
        }

        String jwtToken = database.loginUser(user);
        return new LoginRes("Login successful", jwtToken, user);
    }

    @PostMapping("/logout")
    public Response logoutUser(@RequestHeader("Authorization") String authHeader) {
        System.out.println("logout");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ErrorRes("Authorization header is missing or invalid");
        }

        String jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
        System.out.println("token=" + jwtToken);
        boolean isLoggedOut = database.logoutUser(jwtToken);
        if (isLoggedOut) {
            return new MessageRes("Logout successful");
        } else {
            return new ErrorRes("Invalid or expired token");
        }
    }
}
