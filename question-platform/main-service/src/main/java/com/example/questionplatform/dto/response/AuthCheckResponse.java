package com.example.questionplatform.dto.response;

import com.example.questionplatform.model.User;

import lombok.Data;

@Data
public class AuthCheckResponse implements Response {
    private String message;
    private User user;
    
    public AuthCheckResponse() {}
    
    public AuthCheckResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
} 