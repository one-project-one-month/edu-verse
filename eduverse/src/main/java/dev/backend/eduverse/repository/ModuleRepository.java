package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.CourseModule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {

  List<CourseModule> findByCourseId(Long courseId);
}
