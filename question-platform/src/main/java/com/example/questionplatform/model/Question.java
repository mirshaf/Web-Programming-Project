package com.example.questionplatform.model;

import java.util.ArrayList;
import java.util.List;

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

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public Difficulty_Level getDifficulty_level() {
        return difficulty_level;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public List<Integer> getRelated_question_ids() {
        return related_question_ids;
    }
}
