package com.example.questionplatform.controller;

import com.example.questionplatform.model.Answer;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
import com.example.questionplatform.dto.request.AnswerReq;
import com.example.questionplatform.dto.response.AnswerRes;
import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.Response;
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
        Integer value = 0;
        boolean is_correct = false;
        if (correctAns.equals(answerReq.getSelected_option())) {
            value = switch (question.getDifficulty_level()) {
                case easy -> 1;
                case medium -> 2;
                case hard -> 3;
            };
            is_correct = true;
        }
        Answer answer = new Answer(question.getId(), user.getId(), answerReq.getSelected_option(), is_correct);
        user.addAnsweredQuestion(question.getId(), value, answer.getId());
        database.addAnswer(answer);

        return new AnswerRes("Answer submitted successfully", correctAns, is_correct);
    }
}
