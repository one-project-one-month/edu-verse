package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query(value = "SELECT t FROM Token t WHERE t.token = ?1")
    Token findByToken(String token);
}
