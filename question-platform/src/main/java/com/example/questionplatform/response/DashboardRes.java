package com.example.questionplatform.response;

import com.example.questionplatform.model.User;

public class DashboardRes implements Response {
    UserRes2 user;

    public DashboardRes(User user) {
        this.user = new UserRes2(user);
    }

    public UserRes2 getUser() {
        return user;
    }
}
