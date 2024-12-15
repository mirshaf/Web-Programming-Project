package com.example.questionplatform.response;

public class ErrorRes implements Response{
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorRes(String error) {
        this.error = error;
    }
}
