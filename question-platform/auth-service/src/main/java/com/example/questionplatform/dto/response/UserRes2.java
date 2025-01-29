package com.example.questionplatform.dto.response;

import com.example.questionplatform.model.User;
import lombok.Getter;

@Getter
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
}