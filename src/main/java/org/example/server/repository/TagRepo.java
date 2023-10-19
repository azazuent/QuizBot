package org.example.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {

   // @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
   // User getByName(@Param("tag") String login);
}
