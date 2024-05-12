package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.userCourseDTO;
import java.util.List;

public interface userCourseServices {
  userCourseDTO createUserCourse(userCourseDTO userCourseDTO);

  userCourseDTO getUserCourseById(long id);

  List<userCourseDTO> getAllUserCourses();

  List<userCourseDTO> getUserCoursesByUserId(long userId);

  List<userCourseDTO> getUserCoursesByCourseId(long courseId);

  userCourseDTO updateUserCourse(userCourseDTO userCourseDTO);

  void deleteUserCourse(long id);
}
