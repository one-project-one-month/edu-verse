package dev.backend.eduverse.repository;

import dev.backend.eduverse.model.UserCourse;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourse> findByUserId(long userId);

    List<UserCourse> findByCourseId(long courseId);

    @Query("SELECT c.name, e.createdDate " +
            "FROM UserCourse e " +
            "JOIN e.user u " +
            "JOIN e.course c " +
            "WHERE u.id = :userId")
    List<Object[]> findCoursesAndEnrollmentDateByUserId(@Param("userId") Long userId);

    //
    UserCourse findByUserIdAndCourseId(Long user_id, Long course_id);
}
