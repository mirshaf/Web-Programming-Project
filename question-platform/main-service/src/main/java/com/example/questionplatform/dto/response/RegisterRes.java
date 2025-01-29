package com.example.questionplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter // Setters?
public class RegisterRes implements Response{
    private String message;
    private UserRes user;
}
