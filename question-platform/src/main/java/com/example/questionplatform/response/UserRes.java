package com.example.questionplatform.response;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
