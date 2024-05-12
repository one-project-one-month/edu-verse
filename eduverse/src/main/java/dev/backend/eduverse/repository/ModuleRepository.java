package dev.backend.eduverse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.backend.eduverse.model.CourseModule;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {

	List<CourseModule> findByCourseId(Long courseId);

}
