package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.request.QuestionDTO;
import com.example.questionplatform.dto.response.AddQuestionRes;
import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.GetAnsweredRes;
import com.example.questionplatform.dto.response.MessageRes;
import com.example.questionplatform.dto.response.QuestionRes;
import com.example.questionplatform.dto.response.QuestionTextDTO;
import com.example.questionplatform.dto.response.QuestionsDTO;
import com.example.questionplatform.dto.response.QuestionsRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;
import com.example.questionplatform.service.AuthorizationService;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    @Autowired
    Database database;

    @Autowired
    AuthorizationService authorizationService;

    @GetMapping()
    public Response getQuestions(@RequestHeader("Authorization") String authHeader,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) String difficulty) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        
        if (!authorizationService.isPlayer(user)) {
            return new ErrorRes("Only players can view questions");
        }
        
        return new QuestionsRes(database.getQuestions(category, difficulty, user), database);
    }

    @GetMapping("/feed")
    public Response getFeed(@RequestHeader("Authorization") String authHeader) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        return new QuestionsRes(database.getFeed(user), database);
    }

    @GetMapping("/{id}")
    public Response getQuestion(@RequestHeader("Authorization") String authHeader,
                                @PathVariable("id") Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        if (!authorizationService.isPlayer(user)) {
            return new ErrorRes("Only players can view questions");
        }

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
        try {
            User user = database.getUser(authHeader);
            if (user == null)
                return new ErrorRes("Unauthenticated");

            if (!authorizationService.canCreateQuestion(user)) {
                return new ErrorRes("Only designers can create questions");
            }

            if (questionDTO == null || questionDTO.getCategory() == null) {
                return new ErrorRes("Question data and category are required");
            }

            questionDTO.setCreated_by(user.getId());
            
            QuestionTextDTO question = database.createQuestion(questionDTO);
            if (question == null) {
                return new ErrorRes("Failed to create question. Category not found.");
            }
            return new AddQuestionRes("Question created successfully", question);
        } catch (Exception e) {
            return new ErrorRes("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Response editQuestion(@RequestHeader("Authorization") String authHeader,
                                 @RequestBody QuestionDTO questionDTO,
                                 @PathVariable("id") Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Question existingQuestion = database.getQuestionById(id);
        if (existingQuestion == null) {
            return new ErrorRes("Question not found");
        }

        if (!authorizationService.canEditQuestion(user, existingQuestion.getCreated_by())) {
            return new ErrorRes("You can only edit your own questions");
        }

        QuestionTextDTO question = database.editQuestion(id, questionDTO);
        if (question == null) {
            return new ErrorRes("Failed to update question");
        }
        return new AddQuestionRes("Question updated successfully", question);
    }

    @DeleteMapping("/{id}")
    public Response deleteQuestion(@RequestHeader("Authorization") String authHeader,
                                 @PathVariable("id") Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        Question existingQuestion = database.getQuestionById(id);
        if (existingQuestion == null) {
            return new ErrorRes("Question not found");
        }

        if (!authorizationService.canDeleteQuestion(user, existingQuestion.getCreated_by())) {
            return new ErrorRes("You can only delete your own questions");
        }

        Boolean result = database.deleteQuestion(id);
        if (result == null) {
            return new ErrorRes("Question not found");
        } else if (!result) {
            return new ErrorRes("Question cannot be deleted as it is referenced by other questions");
        }
        return new MessageRes("Question deleted successfully");
    }

    @GetMapping("/my")
    public Response getMyQuestions(@RequestHeader("Authorization") String authHeader,
                                    @RequestParam(required = false) String category,
                                    @RequestParam(required = false) String difficulty) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        if (!authorizationService.isDesigner(user)) {
            return new ErrorRes("Only designers can view their questions");
        }

        return new QuestionsDTO(database.getQuestionsByUser(user.getId(), category, difficulty));
    }
}
