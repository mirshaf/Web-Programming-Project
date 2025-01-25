package com.example.questionplatform.dto.response;

import com.example.questionplatform.model.User;

public class LoginRes implements Response {
    private String message;
    private String token;
    private UserLoginRes user;

    public LoginRes(String message, String token, User user) {
        this.message = message;
        this.token = token;
        this.user = new UserLoginRes(user);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserLoginRes getUser() {
        return user;
    }

    public void setUser(UserLoginRes user) {
        this.user = user;
    }

    private class UserLoginRes { //todo: use UserRes2 instead
        private int id;
        private String username;
        private String email;
        private String avatar_url;
        private String role;
        private int points;
        private int followings;
        private int followers;

        public UserLoginRes(User user) {
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
}
