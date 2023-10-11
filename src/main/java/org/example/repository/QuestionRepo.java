package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepo extends JpaRepository<Question, Long>{

    @Query("SELECT q.question FROM Question q ORDER BY RAND() LIMIT 1")
    String getRandomQuestion();

    @Query("SELECT q.question FROM Question q " +
            "INNER JOIN QuestionTag qt on qt.questionId = q.id " +
            "INNER JOIN Tag t on qt.tagId = t.id " +
            "WHERE t.tag = :tag " +
            "ORDER BY RAND() LIMIT 1")
    String getRandomQuestionByTag(@Param("tag") String tag);
}
