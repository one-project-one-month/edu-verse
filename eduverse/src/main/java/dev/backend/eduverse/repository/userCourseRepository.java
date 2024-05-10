package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface userCourseRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourse> findByUserId(long userId);

    List<UserCourse> findByCourseId(long courseId);
}


