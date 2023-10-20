package org.example.server.repository.repo;

import org.example.server.repository.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepo extends JpaRepository<Question, Long>{

    @Query("SELECT q FROM Question q ORDER BY RAND() LIMIT 1")
    Question getRandomQuestion();

    @Query("SELECT q FROM Question q " +
            "INNER JOIN QuestionTag qt on qt.questionId = q.id " +
            "INNER JOIN Tag t on qt.tagId = t.id " +
            "WHERE t.tag = :tag " +
            "ORDER BY RAND() LIMIT 1")
    Question getRandomQuestionByTag(@Param("tag") String tag);

    @Query("SELECT q.id FROM Question q WHERE q.question = :question")
    Long getQuestionId(@Param("question") String question);

}
