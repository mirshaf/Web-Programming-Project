package com.example.questionplatform.dto.response;

import lombok.Data;

@Data
public class QuestionTextDTO {
    private Integer id;
    private String text;

    public QuestionTextDTO(Integer id, String text) {
        this.id = id;
        this.text = text;
    }
}
