/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query(value = "SELECT * FROM course ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Course> paginate(@Param("limit") int limit, @Param("offset") int offset);
}
