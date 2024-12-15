package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.QuestionRes;
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
        return new QuestionsRes(database.getQuestions(category, difficulty), database);
    }

    @GetMapping("/{id}")
    public Response getQuestion(@RequestHeader("Authorization") String authHeader,
                                @PathVariable("id") Integer id){
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        Question question = database.getQuestionById(id);
        if (question == null) {
            return new ErrorRes("Question not found");
        }
        return new QuestionRes(question, database);
    }
}
