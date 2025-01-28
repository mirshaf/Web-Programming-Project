package com.example.questionplatform.dto.request;

import java.util.List;

import com.example.questionplatform.model.Question;

import lombok.Data;

@Data
public class QuestionDTO {
    private String text;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correct_answer;
    private Question.Difficulty_Level difficulty_level;
    private String category;
    private List<Integer> related_question_ids;
    private Integer created_by;
}