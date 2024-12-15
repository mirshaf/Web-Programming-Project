package com.example.questionplatform.response;

public class RegisterRes implements Response{
    private String message;
    private UserRes user;

    public RegisterRes(String message, UserRes user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserRes getUser() {
        return user;
    }

    public void setUser(UserRes user) {
        this.user = user;
    }
}
