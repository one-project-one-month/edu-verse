/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 8:17 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Question;
import dev.backend.eduverse.model.UserExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserExamRepository extends JpaRepository<UserExam, Long> {

    @Query(value = "SELECT * FROM user_exam ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<UserExam> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
