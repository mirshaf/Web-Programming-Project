package com.example.questionplatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follows")
@Getter
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "follower_id", nullable = false)
    private Integer follower_id;

    @Column(name = "following_id", nullable = false)
    private Integer following_id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "follower_id", insertable = false, updatable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", insertable = false, updatable = false)
    private User following;

    public Follow(Integer follower_id, Integer following_id) {
        this.follower_id = follower_id;
        this.following_id = following_id;
        this.created_at = LocalDateTime.now();
    }
} 