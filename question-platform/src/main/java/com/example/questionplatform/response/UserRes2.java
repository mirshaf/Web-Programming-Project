package com.example.questionplatform.response;

import com.example.questionplatform.model.User;

public class UserRes2 {
    private int id;
    private String username;
    private String email;
    private String avatar_url;
    private String role;
    private int points;
    private int followings;
    private int followers;

    public UserRes2(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.avatar_url = user.getAvatar_url();
        this.role = user.getRole().toString();
        this.points = user.getPoints();
        this.followings = user.getFollowings();
        this.followers = user.getFollowers();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getRole() {
        return role;
    }

    public int getPoints() {
        return points;
    }

    public int getFollowings() {
        return followings;
    }

    public int getFollowers() {
        return followers;
    }
}