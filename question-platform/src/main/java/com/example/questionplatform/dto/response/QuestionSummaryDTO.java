package com.example.questionplatform.dto.response;

import lombok.Data;

@Data
public class QuestionSummaryDTO {
    private Integer id;
    private String text;
    private String category;
    private String difficulty;

    // Constructors
    public QuestionSummaryDTO() {
    }

    public QuestionSummaryDTO(Integer id, String text, String category, String difficulty) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.difficulty = difficulty;
    }
}