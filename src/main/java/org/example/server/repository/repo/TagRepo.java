package org.example.server.repository.repo;

import org.example.server.repository.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepo extends JpaRepository<Tag, Long> {
    @Query("SELECT t.id FROM Tag t WHERE t.tag = :tag")
    Long getTagId(@Param("tag") String tag);
}
