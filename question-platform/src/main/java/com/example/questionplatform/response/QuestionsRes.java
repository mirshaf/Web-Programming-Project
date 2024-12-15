package com.example.questionplatform.response;

import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsRes implements Response {
    List<QuestionRes> questions;

    public List<QuestionRes> getQuestions() {
        return questions;
    }

    public QuestionsRes(List<Question> questions, Database database) {
        this.questions = new ArrayList<>();
        for (Question question :
                questions) {
            this.questions.add(new QuestionRes(question, database));
        }
    }

    private class QuestionRes {
        private Integer id;
        private String text;
        private String category;
        private String difficulty;

        public QuestionRes(Question question, Database database) {
            this.id = question.getId();
            this.text = question.getText();
            this.category = database.getCategoryById(question.getCategory_id()).getName();
            this.difficulty = question.getDifficulty_level().toString();
        }
    }
}
