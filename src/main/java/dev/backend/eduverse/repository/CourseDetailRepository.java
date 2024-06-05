/**
 * @Author : Kyaw Zaw Htet @Date : 5/11/2024 @Time : 10:44 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    @Query(value = "SELECT * FROM course_details ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<CourseDetail> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
