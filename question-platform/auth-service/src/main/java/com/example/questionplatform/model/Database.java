package com.example.questionplatform.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.questionplatform.repository.UserRepository;
import com.example.questionplatform.service.TokenService;
import com.example.questionplatform.util.JwtUtil;

@Service
public class Database {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public Database() {
    }

    // User

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(String username, String email, String password, String avatar_url, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        user.setAvatar_url(avatar_url);
        return userRepository.save(user);
    }

    public String loginUser(User user) {
        String jwtToken = JwtUtil.generateToken(user.getEmail());
        tokenService.saveToken(jwtToken, user.getEmail());
        return jwtToken;
    }

    public User getUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
        String userEmail = tokenService.getUserEmailFromToken(jwtToken);
        if (userEmail == null) {
            return null;
        }
        return getUserByEmail(userEmail);
    }

    public boolean logoutUser(String jwtToken) {
        if (tokenService.isTokenValid(jwtToken)) {
            tokenService.removeToken(jwtToken);
            return true;
        }
        return false;
    }

    public boolean isTokenValid(String jwtToken) {
        return tokenService.isTokenValid(jwtToken);
    }

    public List<User> getUsers(String username, User requester) {
        if (username == null) {
            return userRepository.findAll().stream()
                .filter(u -> !u.getId().equals(requester.getId()))
                .toList();
        }
        return userRepository.findAll().stream()
                .filter(u -> !u.getId().equals(requester.getId()))
                .filter(u -> u.getUsername().equals(username))
                .toList();
    }

    public List<User> getAllUsersForLeaderboard() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}