package com.example.questionplatform.response;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;

public class QuestionRes implements Response {
    private Integer id;
    private String text;
    private String category;
    private String difficulty;
    private String option1, option2, option3, option4;

    public QuestionRes(Question question, Database database) {
        this.id = question.getId();
        this.text = question.getText();
        this.category = database.getCategoryById(question.getCategory_id()).getName();
        this.difficulty = question.getDifficulty_level().toString();
        this.option1 = question.getOption1();
        this.option2 = question.getOption2();
        this.option3 = question.getOption3();
        this.option4 = question.getOption4();
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
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
}
