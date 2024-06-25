/*
 * @Author : Alvin
 * @Date : 6/9/2024
 * @Time : 10:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Question> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
