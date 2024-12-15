package com.example.questionplatform.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Database {
    private final List<User> users = new ArrayList<>();

    public User getUserByEmail(String email) {
        for(User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public User registerUser(String username, String email, String password, String avatar_url, String role) {
        User user = new User(username, password, role, email, avatar_url);
        users.add(user);
        return user;
    }
}
