package com.example.questionplatform.request;

public class AnswerReq {
    private Integer question_id;
    private String selected_option;

    public Integer getQuestion_id() {
        return question_id;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public AnswerReq(Integer question_id, String selected_option) {
        this.question_id = question_id;
        this.selected_option = selected_option;
    }
}
