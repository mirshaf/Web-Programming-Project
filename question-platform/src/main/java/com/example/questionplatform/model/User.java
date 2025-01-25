package com.example.questionplatform.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password; //todo: hash
    private String role;
    private String email;
    private int points = 0;
    private String avatar_url;
    private int followings = 0, followers = 0;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> followingIds = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> answeredQuestionsIds = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> answerIds = new ArrayList<>();

    public boolean comparePassword(String password) {
        return this.password.equals(password); //todo: use hash
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
