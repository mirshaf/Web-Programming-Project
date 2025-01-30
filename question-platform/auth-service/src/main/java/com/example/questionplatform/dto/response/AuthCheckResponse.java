package com.example.questionplatform.dto.response;

import com.example.questionplatform.model.User;

import lombok.Data;

@Data
public class AuthCheckResponse implements Response {
    private String message;
    private UserRes user;
    
    public AuthCheckResponse() {}
    
    public AuthCheckResponse(String message, User user) {
        this.message = message;
        if (user != null) {
            this.user = new UserRes(user.getId(), user.getUsername(), user.getEmail(), 
                user.getAvatar_url(), user.getRole().toString(), user.getPoints());
        }
    }
} 