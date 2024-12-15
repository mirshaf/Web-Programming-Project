package com.example.questionplatform.model;

public class Token {
    //todo: JWT
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token) {
        this.token = token;
    }
}
