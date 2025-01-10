package com.example.questionplatform.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ErrorRes implements Response{
    private String error;
}
