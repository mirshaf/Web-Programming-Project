package com.example.questionplatform.dto.response;

public class AnswerRes implements Response {
    private String message;
    private String correct_answer;
    private boolean is_correct;

    public String getMessage() {
        return message;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public AnswerRes(String message, String correct_answer, boolean is_correct) {
        this.message = message;
        this.correct_answer = correct_answer;
        this.is_correct = is_correct;
    }
}
