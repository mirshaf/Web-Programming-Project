package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.request.RegisterReq;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.RegisterRes;
import com.example.questionplatform.response.Response;
import com.example.questionplatform.response.UserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
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
}
