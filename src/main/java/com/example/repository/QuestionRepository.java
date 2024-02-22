package com.example.repository;

import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT batata FROM Question batata WHERE batata.isPlayed = false ORDER BY RANDOM() LIMIT 1")
    Question findRandonQuestion();
}