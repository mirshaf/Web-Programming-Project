package com.example.questionplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionplatform.dto.response.ErrorRes;
import com.example.questionplatform.dto.response.GetUsersRes;
import com.example.questionplatform.dto.response.MessageRes;
import com.example.questionplatform.dto.response.Response;
import com.example.questionplatform.dto.response.UserRes3;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    Database database;

    @GetMapping()
    public Response getUsers(@RequestHeader("Authorization") String authHeader,
                             @RequestParam(required = false) String query) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");
        return new GetUsersRes(database.getUsers(query, user), user);
    }

    @GetMapping("/{id}")
    public Response getUser(@RequestHeader("Authorization") String authHeader,
                            @PathVariable Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        User other = database.getUserById(id);
        if (other == null) {
            return new ErrorRes("User not found");
        }

        return new UserRes3(other, user);
    }

    @PostMapping("/{id}/follow")
    public Response followUser(@RequestHeader("Authorization") String authHeader,
                               @PathVariable Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        User other = database.getUserById(id);
        if (other == null) {
            return new ErrorRes("User not found");
        }

        if (! user.addFollowing(other.getId())) {
            return new ErrorRes("Already following");
        }
        other.addFollower();
        return new MessageRes("User followed successfully");
    }

    @PostMapping("/{id}/unfollow")
    public Response unfollowUser(@RequestHeader("Authorization") String authHeader,
                               @PathVariable Integer id) {
        User user = database.getUser(authHeader);
        if (user == null)
            return new ErrorRes("Unauthenticated");

        User other = database.getUserById(id);
        if (other == null) {
            return new ErrorRes("User not found");
        }

        if (! user.removeFollowing(other.getId())) {
            return new ErrorRes("Was not following");
        }
        other.removeFollower();
        return new MessageRes("User unfollowed successfully");
    }
}
