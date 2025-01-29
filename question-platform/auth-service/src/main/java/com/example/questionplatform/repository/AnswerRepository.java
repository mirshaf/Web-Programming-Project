package com.example.questionplatform.repository;

import com.example.questionplatform.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByPlayerId(Integer playerId);
    List<Answer> findByPlayerIdAndQuestionId(Integer playerId, Integer questionId);
} 