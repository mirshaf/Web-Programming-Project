package com.example.questionplatform.dto.response;

import lombok.Data;

@Data
public class AddQuestionRes implements Response {
    private String message;
    private QuestionTextDTO question;

    public AddQuestionRes(String message, QuestionTextDTO question) {
        this.message = message;
        this.question = question;
    }
}
