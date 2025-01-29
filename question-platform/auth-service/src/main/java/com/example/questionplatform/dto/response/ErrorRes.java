package com.example.questionplatform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ErrorRes implements Response{
    private String error;
}
