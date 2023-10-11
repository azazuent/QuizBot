package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagRepo extends JpaRepository<Tag, Long> {

   // @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
   // User getByName(@Param("tag") String login);
}
