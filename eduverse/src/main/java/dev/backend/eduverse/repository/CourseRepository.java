/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "SELECT * FROM course ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Course> paginate(@Param("limit") int limit, @Param("offset") int offset);

    Optional<Course> findByName(String name);

    @Query("SELECT c FROM Course c WHERE c.name LIKE %:name%")
    List<Course> findByNameContaining(@Param("name") String name);    

    @Query(value = "SELECT c.* FROM course c LEFT JOIN course_details cd ON c.id = cd.course_id ORDER BY c.id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Course> findAllWithDetailsPaginated(@Param("limit") int limit, @Param("offset") int offset);
}
