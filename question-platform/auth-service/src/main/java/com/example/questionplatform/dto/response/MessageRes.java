package com.example.questionplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class MessageRes implements Response {
    private String message;
}
