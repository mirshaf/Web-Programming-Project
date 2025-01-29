package com.example.questionplatform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.questionplatform.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findByFollowerIdAndFollowingId(Integer followerId, Integer followingId);
} 