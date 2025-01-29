package com.example.questionplatform.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.example.questionplatform.model.Answer;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;

public class GetAnsweredRes implements Response {
    List<QuestionRes> questions;

    public GetAnsweredRes(User user, Database database) {
        this.questions = new ArrayList<>();
        List<Answer> answers = database.getAnswersByUser(user.getId());
        int i = 0;
        for (Answer answer : answers) {
            Question question = database.getQuestionById(answer.getQuestion_id());
            if (question != null) {
                QuestionRes questionRes = new QuestionRes(question, database, answer.getSelected_option(), answer.getIs_correct());
                questions.add(questionRes);
                if (++i >= 20) {
                    break;
                }
            }
        }
    }

    public List<QuestionRes> getQuestions() {
        return questions;
    }
}
