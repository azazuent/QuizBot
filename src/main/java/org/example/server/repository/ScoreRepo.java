package org.example.server.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepo extends JpaRepository<Score, Long> {

    @Query("SELECT count(*) FROM Score s WHERE s.userId = :id")
    Integer getScoreById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Score s WHERE s.userId = :id")
    Integer deleteScoreById(@Param("id") Long id);

    @Query("SELECT count(*) FROM User u "+
    "INNER JOIN Score s on s.userId = u.id "+
    "INNER JOIN Question q on s.questionId = q.id "+
    "INNER JOIN QuestionTag qt on qt.questionId = q.id "+
    "INNER JOIN Tag t on qt.tagId = t.id "+
    "WHERE u.id = :id AND t.tag = :tag")
    Integer getScoreByIdAndTag(@Param("id") Long id, @Param("tag") String tag);
}