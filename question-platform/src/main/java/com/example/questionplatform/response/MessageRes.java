package com.example.questionplatform.response;

public class MessageRes implements Response {
    private String message;

    public String getMessage() {
        return message;
    }

    public MessageRes(String message) {
        this.message = message;
    }
}
