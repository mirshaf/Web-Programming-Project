package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.response.DashboardRes;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.QuestionsRes;
import com.example.questionplatform.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
