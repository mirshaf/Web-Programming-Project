package com.example.questionplatform.dto.response;

import lombok.Data;

@Data
public class AuthCheckResponse implements Response {
    private String message;
    
    public AuthCheckResponse() {}
    
    public AuthCheckResponse(String message) {
        this.message = message;
    }
} 