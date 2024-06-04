package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Exam;
import dev.backend.eduverse.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = "SELECT * FROM exam ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Exam> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
