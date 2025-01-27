package com.example.questionplatform.controller;

import com.example.questionplatform.dto.request.QuestionDTO;
import com.example.questionplatform.dto.response.*;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
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
        return new QuestionRes(question, database, null, false);
    }

    @GetMapping("/random")
    public Response getRandomQuestion(@RequestHeader("Authorization") String authHeader){
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Question question = database.getRandomQuestion();
        if (question == null) {
            return new MessageRes("No questions available");
        }
        return new QuestionRes(question, database, null, false);
    }

    @GetMapping("/answered")
    public Response getAnsweredQuestions(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        return new GetAnsweredRes(user, database);
    }

    @PostMapping()
    public Response addQuestion(@RequestHeader("Authorization") String authHeader,
                                @RequestBody QuestionDTO questionDTO) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        QuestionTextDTO question = database.createQuestion(questionDTO);
        if (question == null) {
            return new ErrorRes("Category not found.");
        }
        return new AddQuestionRes("Question created successfully", question);
    }

    @PutMapping("/{id}")
    public Response editQuestion(@RequestHeader("Authorization") String authHeader,
                                 @RequestBody QuestionDTO questionDTO,
                                 @PathVariable("id") Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        QuestionTextDTO question = database.editQuestion(id, questionDTO);
        if (question == null) {
            return new ErrorRes("Question or category not found.");
        }
        return new AddQuestionRes("Question updated successfully", question);
    }

    @DeleteMapping("/{id}")
    public Response editQuestion(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable("id") Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Boolean result = database.deleteQuestion(id);
        if (result == null) {
            return new ErrorRes("Question not found");
        } else if (!result) {
            return new ErrorRes("Question cannot be deleted as it is referenced by other questions");
        }
        return new MessageRes("Question deleted successfully");
    }

    @GetMapping("/my")
    public Response getMyQuestions(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        return new QuestionsDTO(database.getQuestionsByUser(user.getId()));
    }
}
