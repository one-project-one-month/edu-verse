/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 10:42 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
