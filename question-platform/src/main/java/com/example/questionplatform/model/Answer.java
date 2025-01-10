package com.example.questionplatform.model;

import lombok.Getter;

@Getter
public class Answer {
    static Integer idCounter = 1;
    private Integer id;
    private Integer question_id;
    private Integer player_id;
    private String selected_option;
    private Boolean is_correct;

    public Answer(Integer question_id, Integer player_id, String selected_option, Boolean is_correct) {
        this.id = idCounter++;
        this.question_id = question_id;
        this.player_id = player_id;
        this.selected_option = selected_option;
        this.is_correct = is_correct;
    }
}
