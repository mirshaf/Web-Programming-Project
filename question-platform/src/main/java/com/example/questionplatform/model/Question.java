package com.example.questionplatform.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Question {
    static int idCounter = 1;
    private Integer id;
    private String text;
    private String option1, option2, option3, option4;
    private int correct_answer;
    private Difficulty_Level difficulty_level;
    private Integer created_by; // User Id
    private Integer category_id;
    private List<Integer> related_question_ids;

    public Question(String text, String option1, String option2, String option3, String option4, int correct_answer, Difficulty_Level difficulty_level, Integer created_by, Integer category_id) {
        this.id = idCounter++;
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct_answer = correct_answer;
        this.difficulty_level = difficulty_level;
        this.created_by = created_by;
        this.category_id = category_id;
        this.related_question_ids = new ArrayList<>();
    }

    public enum Difficulty_Level {
        easy,
        medium,
        hard
    }

    public String getCorrectOption() {
        return switch (correct_answer) {
            case 1 -> option1;
            case 2 -> option2;
            case 3 -> option3;
            case 4 -> option4;
            default -> null;
        };
    }
}
