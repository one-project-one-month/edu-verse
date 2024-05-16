package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.CourseModule;
import java.util.List;

import org.aspectj.weaver.tools.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModule, Long> {

	List<CourseModule> findByCourseId(Long courseId);

	@Query(value = "SELECT * FROM module ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<CourseModule> getAllModulesByPagination(@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);
}
