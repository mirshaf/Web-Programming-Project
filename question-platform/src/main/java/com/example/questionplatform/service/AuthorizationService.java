package com.example.questionplatform.service;

import org.springframework.stereotype.Service;

import com.example.questionplatform.model.User;

@Service
public class AuthorizationService {
    
    public static final String ROLE_PLAYER = "player";
    public static final String ROLE_DESIGNER = "designer";

    public boolean isDesigner(User user) {
        return user != null && ROLE_DESIGNER.equals(user.getRole());
    }

    public boolean isPlayer(User user) {
        return user != null && ROLE_PLAYER.equals(user.getRole());
    }

    public boolean canCreateQuestion(User user) {
        return isDesigner(user);
    }

    public boolean canEditQuestion(User user, Integer questionCreatorId) {
        return isDesigner(user) && user.getId().equals(questionCreatorId);
    }

    public boolean canDeleteQuestion(User user, Integer questionCreatorId) {
        return isDesigner(user) && user.getId().equals(questionCreatorId);
    }

    public boolean canAnswerQuestion(User user) {
        return isPlayer(user);
    }

    public boolean canViewLeaderboard(User user) {
        return isPlayer(user) || isDesigner(user);
    }

    public boolean canCreateCategory(User user) {
        return isDesigner(user);
    }
} 