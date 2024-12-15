package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.GetUsersRes;
import com.example.questionplatform.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    Database database;

    @GetMapping()
    public Response getUsers(@RequestHeader("Authorization") String authHeader,
                             @RequestParam(required = false) String query) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        return new GetUsersRes(database.getUsers(query), user);
    }
}
