package com.example.questionplatform.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    static int idCounter = 1;
    private Integer id;
    private String username;
    private String password; //todo: hash
    private Role role;
    private String email;
    private int points;
    private String avatar_url;
    private int followings, followers;
    private List<Integer> followingIds;
    private List<Integer> answeredQuestionsIds;
    private List<Integer> answerIds;

    public enum Role {
        designer,
        player
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password); //todo: use hash
    }

    public User(String username, String password, String role, String email, String avatar_url) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        switch (role) {
            case "designer" -> this.role = Role.designer;
            case "player" -> this.role = Role.player;
            default -> {
            } //todo
        }
        this.email = email;
        this.points = 0;
        this.avatar_url = avatar_url;
        this.followers = 0;
        this.followings = 0;
        this.followingIds = new ArrayList<>();
        this.answeredQuestionsIds = new ArrayList<>();
        this.answerIds = new ArrayList<>();
    }

    public boolean hasAnswered(Integer questionId) {
        return this.answeredQuestionsIds.contains(questionId);
    }

    public boolean addAnsweredQuestion(Integer questionId, Integer value, Integer answerId) {
        if (this.answeredQuestionsIds.contains(questionId)) {
            return false;
        }

        this.answeredQuestionsIds.add(questionId);
        this.answerIds.add(answerId);
        this.points += value;
        return true;
    }

    public boolean addFollowing(Integer id) {
        if (this.followingIds.contains(id)) {
            return false;
        }
        this.followings++;
        this.followingIds.add(id);
        System.out.println(this.id + " followed " + id);
        return true;
    }

    public boolean removeFollowing(Integer id) {
        if (! this.followingIds.remove(id)) {
            return false;
        }
        this.followings--;
        System.out.println(this.id + " unfollowed " + id);
        return true;
    }

    public void addFollower() {
        this.followers++;
    }

    public void removeFollower() {
        this.followers--;
    }

    public Boolean follows(Integer id) {
        return this.followingIds.contains(id);
    }
}
