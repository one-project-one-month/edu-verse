package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
