package com.example.questionplatform.model;

import com.example.questionplatform.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Database {
    private final List<User> users = new ArrayList<>();
    private final Map<String, User> loggedInUsers = new HashMap<>();

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

    public String loginUser(User user) {
//        Token token = new Token(String.valueOf(user.getId() * 37) + " " + LocalTime.now());
//        loggedInUsers.put(token, user);
//        return token.getToken();
        String jwtToken = JwtUtil.generateToken(user.getEmail());
        loggedInUsers.put(jwtToken, user);
        return jwtToken;
    }

    public User getUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
        return loggedInUsers.get(jwtToken);
    }

    public boolean logoutUser(String jwtToken) {
        return loggedInUsers.remove(jwtToken) != null;
    }

    public List<User> getUsers() {
        return users;
    }
}
