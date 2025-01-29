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
@Table(name = "answers")
@Getter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id", nullable = false)
    private Integer question_id;

    @Column(name = "player_id", nullable = false)
    private Integer player_id;

    @Column(name = "selected_option", nullable = false)
    private String selected_option;

    @Column(name = "is_correct", nullable = false)
    private Boolean is_correct;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private User player;

    public Answer(Integer question_id, Integer player_id, String selected_option, Boolean is_correct) {
        this.question_id = question_id;
        this.player_id = player_id;
        this.selected_option = selected_option;
        this.is_correct = is_correct;
        this.created_at = LocalDateTime.now();
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public Integer getPlayer_id() {
        return player_id;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }
}
