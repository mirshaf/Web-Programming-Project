package com.example.questionplatform.response;

import com.example.questionplatform.model.User;

public class UserRes3 implements Response { //todo this is the most complete. replace other versions with this
    private int id;
    private String username;
//    private String email;
    private int points;
    private String role;
    private String avatar_url;
    private int followers;
    private int followings;
    private Boolean followed;

    public UserRes3(User user, User requester) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.points = user.getPoints();
        this.role = user.getRole().toString();
        this.avatar_url = user.getAvatar_url();
        this.followers = user.getFollowers();
        this.followings = user.getFollowings();
        this.followed = requester.follows(user.getId());
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getPoints() {
        return points;
    }

    public String getRole() {
        return role;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowings() {
        return followings;
    }

    public Boolean getFollowed() {
        return followed;
    }
}
