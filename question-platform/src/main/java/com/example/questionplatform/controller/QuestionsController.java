package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.QuestionsRes;
import com.example.questionplatform.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    @Autowired
    Database database;

    @GetMapping()
    public Response getQuestions(@RequestHeader("Authorization") String authHeader,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) String difficulty) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        //todo: test if params work
        System.out.println(category + " " + difficulty);
        return new QuestionsRes(database.getQuestions(category, difficulty), database);
    }
}
