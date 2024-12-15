package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.GetLeaderboardRes;
import com.example.questionplatform.response.GetUsersRes;
import com.example.questionplatform.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    @Autowired
    Database database;

    @GetMapping()
    public Response getLeaderboard(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        return new GetLeaderboardRes(database.getUsers());
    }
}
