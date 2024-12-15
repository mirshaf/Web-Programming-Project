package com.example.questionplatform.controller;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
import com.example.questionplatform.request.AnswerReq;
import com.example.questionplatform.response.AnswerRes;
import com.example.questionplatform.response.ErrorRes;
import com.example.questionplatform.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
public class AnswersController {
    @Autowired
    Database database;

    @PostMapping()
    public Response answer(@RequestHeader("Authorization") String authHeader,
                           @RequestBody AnswerReq answerReq) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Question question = database.getQuestionById(answerReq.getQuestion_id());
        if (question == null) {
            return new ErrorRes("Question not found");
        }
        if (user.hasAnswered(question.getId())) {
            return new ErrorRes("Question was already answered");
        }

        String correctAns = question.getCorrectOption();
        if (correctAns.equals(answerReq.getSelected_option())) {
            Integer value = switch (question.getDifficulty_level()) {
                case easy -> 1;
                case medium -> 2;
                case hard -> 3;
            };
            user.addAnsweredQuestion(question.getId(), value);
            return new AnswerRes("Answer submitted successfully", correctAns, true);
        }

        return new AnswerRes("Answer submitted successfully", correctAns, false);
    }
}
