package com.example.questionplatform.dto.request;

import lombok.Data;

@Data
public class AnswerReq {
    private Integer question_id;
    private String selected_option;
}
