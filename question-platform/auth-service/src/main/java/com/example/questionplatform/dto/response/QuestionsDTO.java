package com.example.questionplatform.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionsDTO implements Response {
    List<QuestionSummaryDTO> questions;

    public QuestionsDTO(List<QuestionSummaryDTO> questions) {
        this.questions = questions;
    }
}
