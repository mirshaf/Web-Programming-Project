package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.response.DashboardRes;
import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    Database database;

    @GetMapping()
    public Response getQuestions(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        return new DashboardRes(user);
    }
}
