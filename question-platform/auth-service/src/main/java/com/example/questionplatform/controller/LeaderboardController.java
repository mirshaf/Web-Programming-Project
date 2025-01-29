package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.GetLeaderboardRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.service.AuthorizationService;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    @Autowired
    Database database;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping()
    public Response getLeaderboard(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        if (!authorizationService.canViewLeaderboard(user)) {
            return new ErrorRes("You don't have permission to view the leaderboard");
        }

        return new GetLeaderboardRes(database.getAllUsersForLeaderboard());
    }
}
