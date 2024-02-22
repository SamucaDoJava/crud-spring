package com.example.repository;

import com.example.model.Alternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlternativeRepository extends JpaRepository<Alternative, Long> {

    @Query("SELECT pq FROM Alternative pq " +
            "WHERE (pq.isEnable = false OR pq.isEnable IS NULL) " +
            "AND pq.question.id = :questionId ")
    List<Alternative> alternativesByQuestionId(@Param("questionId") Long sessionId);

    @Query("SELECT pq FROM Alternative pq " +
            "WHERE (pq.isEnable = false OR pq.isEnable IS NULL) " +
            "AND pq.itsCorrect = true " +
            "AND pq.question.id = :questionId ")
   Alternative findCorrectAlternative(@Param("questionId") Long sessionId);


}