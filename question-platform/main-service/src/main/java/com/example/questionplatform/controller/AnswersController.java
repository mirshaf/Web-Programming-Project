package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.request.AnswerReq;
import com.example.questionplatform.dto.response.AnswerRes;
import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.model.Answer;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
import com.example.questionplatform.service.AuthorizationService;

@RestController
@RequestMapping("/api/answers")
public class AnswersController {
    @Autowired
    Database database;

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping()
    public Response answer(@RequestHeader("Authorization") String authHeader,
                           @RequestBody AnswerReq answerReq) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        if (!authorizationService.canAnswerQuestion(user)) {
            return new ErrorRes("Only players can answer questions");
        }

        Question question = database.getQuestionById(answerReq.getQuestion_id());
        if (question == null) {
            return new ErrorRes("Question not found");
        }

        String selectedOption = answerReq.getSelected_option();
        if (!selectedOption.equals(question.getCorrectOption())) {
            boolean isCorrect = selectedOption.equals(question.getCorrectOption());
            Answer answer = new Answer(question.getId(), user.getId(), selectedOption, isCorrect);
            Answer savedAnswer = database.addAnswer(answer);
            if (savedAnswer == null) {
                return new ErrorRes("You have already answered this question or an error occurred");
            }
            return new ErrorRes("Invalid option selected");
        }

        boolean isCorrect = selectedOption.equals(question.getCorrectOption());
        Answer answer = new Answer(question.getId(), user.getId(), selectedOption, isCorrect);
        Answer savedAnswer = database.addAnswer(answer);
        if (savedAnswer == null) {
            return new ErrorRes("You have already answered this question or an error occurred");
        }

        if (isCorrect) {
            int points = switch (question.getDifficulty_level()) {
                case easy -> 1;
                case medium -> 2;
                case hard -> 3;
            };
            user.setPoints(user.getPoints() + points);
            database.saveUser(user);
        }

        return new AnswerRes("Answer submitted successfully", selectedOption, isCorrect);
    }
}
