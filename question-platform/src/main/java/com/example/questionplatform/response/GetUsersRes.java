package com.example.questionplatform.response;

import com.example.questionplatform.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetUsersRes implements Response {
    List<UserRes> users;

    public GetUsersRes(List<User> users, User requester) {
        this.users = new ArrayList<>();
        for (User user :
                users) {
            this.users.add(new UserRes(user, requester));
        }
    }

    public List<UserRes> getUsers() {
        return users;
    }

    private class UserRes {
        private Integer id;
        private String username;
        private Integer points;
        private String role;
        private String avatar_url;
        private Boolean followed;

        public UserRes(User user, User requester) {
            this.id = user.getId();
            this.username = user.getUsername();;
            this.points = user.getPoints();
            this.role = user.getRole().toString();
            this.avatar_url = user.getAvatar_url();
            this.followed = requester.follows(user.getId());
        }

        public Integer getId() {
            return id;
        }

        public Boolean getFollowed() {
            return followed;
        }

        public String getUsername() {
            return username;
        }

        public Integer getPoints() {
            return points;
        }

        public String getRole() {
            return role;
        }

        public String getAvatar_url() {
            return avatar_url;
        }
    }
}
