package com.example.questionplatform.response;

import com.example.questionplatform.model.User;
import lombok.Getter;

@Getter
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
}
