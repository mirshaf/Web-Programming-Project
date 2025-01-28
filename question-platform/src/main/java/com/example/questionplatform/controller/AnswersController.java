package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

@RestController
@RequestMapping("/api/answers")
public class AnswersController {
    @Autowired
    Database database;

    @PostMapping()
    @Transactional(rollbackFor = Exception.class)
    public Response answer(@RequestHeader("Authorization") String authHeader,
                           @RequestBody AnswerReq answerReq) {
        try {
            // Step 1: Get and validate user
            User user = database.getUser(authHeader);
            if (user == null) {
                return new ErrorRes("Unauthenticated");
            }
            System.out.println("User found: " + user.getId());

            // Step 2: Get and validate question
            Question question = database.getQuestionById(answerReq.getQuestion_id());
            if (question == null) {
                return new ErrorRes("Question not found");
            }
            System.out.println("Question found: " + question.getId());

            // Step 3: Check if already answered
            if (user.hasAnswered(question.getId())) {
                return new ErrorRes("Question was already answered");
            }

            // Step 4: Process answer
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
            System.out.println("Answer is correct: " + is_correct + ", value: " + value);

            // Step 5: Save answer
            Answer answer = new Answer(question.getId(), user.getId(), answerReq.getSelected_option(), is_correct);
            answer = database.addAnswer(answer);
            if (answer == null) {
                throw new RuntimeException("Failed to save answer to database");
            }
            System.out.println("Answer saved with ID: " + answer.getId());
            
            // Step 6: Update user progress
            if (!user.addAnsweredQuestion(question.getId(), value, answer.getId())) {
                throw new RuntimeException("Failed to update user progress - question might be already answered");
            }
            
            // Step 7: Save user
            User savedUser = database.saveUser(user);
            if (savedUser == null) {
                throw new RuntimeException("Failed to save user after updating progress");
            }
            System.out.println("User progress updated successfully");

            return new AnswerRes("Answer submitted successfully", correctAns, is_correct);
        } catch (Exception e) {
            System.out.println("Error processing answer: " + e.getMessage());
            e.printStackTrace();
            return new ErrorRes("An error occurred while processing your answer: " + e.getMessage());
        }
    }
}
