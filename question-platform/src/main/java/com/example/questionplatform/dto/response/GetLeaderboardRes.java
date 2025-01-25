package com.example.questionplatform.dto.response;

import com.example.questionplatform.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetLeaderboardRes implements Response {
    List<UserRes> leaderboard;

    public GetLeaderboardRes(List<User> users) {
        this.leaderboard = new ArrayList<>();
        users.sort(Comparator.comparingInt(User::getPoints).reversed());
        for (int i = 0; i < users.size(); i++) {
            this.leaderboard.add(new UserRes(users.get(i), i + 1));
        }
    }

    public List<UserRes> getLeaderboard() {
        return leaderboard;
    }

    private class UserRes {
        private Integer id;
        private String username;
        private Integer points;
        private String avatar_url;
        private Integer rank;

        public UserRes(User user, Integer rank) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.points = user.getPoints();
            this.avatar_url = user.getAvatar_url();
            this.rank = rank;
        }

        public Integer getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public Integer getPoints() {
            return points;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public Integer getRank() {
            return rank;
        }
    }
}
