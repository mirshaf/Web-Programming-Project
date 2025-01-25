package com.example.questionplatform.response;

import com.example.questionplatform.model.Answer;
import com.example.questionplatform.model.Database;
import com.example.questionplatform.model.Question;
import com.example.questionplatform.model.User;

import java.util.ArrayList;
import java.util.List;

public class GetAnsweredRes implements Response {
    List<QuestionRes> questions;

    public GetAnsweredRes(User user, Database database) {
        this.questions = new ArrayList<>();
        int i = 0;
        for (Integer id : user.getAnswerIds()) {
            Answer answer = database.getAnswerById(id);
            Question question = database.getQuestionById(answer.getQuestion_id());
            QuestionRes questionRes = new QuestionRes(question, database, answer.getSelected_option(), answer.getIs_correct());
            questions.add(questionRes);
            if (++i >= 20) {
                break;
            }
        }
    }

    public List<QuestionRes> getQuestions() {
        return questions;
    }
}
