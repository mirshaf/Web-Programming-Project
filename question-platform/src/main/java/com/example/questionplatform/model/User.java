package com.example.questionplatform.model;

public class User {
    static int idCounter = 1;
    private Integer id;
    private String username;
    private String password; //todo: hash
    private Role role;
    private String email;
    private int points;
    private String avatar_url;
    private int followings, followers;

    public enum Role {
        designer,
        player
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password); //todo: use hash
    }

    public User(String username, String password, String role, String email, String avatar_url) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        switch (role) {
            case "designer" -> this.role = Role.designer;
            case "player" -> this.role = Role.player;
            default -> {
            } //todo
        }
        this.email = email;
        this.points = 0;
        this.avatar_url = avatar_url;
        this.followers = 0;
        this.followings = 0;
    }

    public Integer getId() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getFollowings() {
        return followings;
    }

    public void setFollowings(int followings) {
        this.followings = followings;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
