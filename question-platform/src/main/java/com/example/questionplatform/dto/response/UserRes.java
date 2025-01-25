package com.example.questionplatform.response;

import lombok.Getter;

@Getter //Setters?
public class UserRes {
    private int id;
    private String username;
    private String email;
    private String avatar_url;
    private String role;
    private int points;

    public UserRes(int id, String username, String email, String avatar_url, String role, int points) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatar_url = avatar_url;
        this.role = role;
        this.points = points;
    }
}
