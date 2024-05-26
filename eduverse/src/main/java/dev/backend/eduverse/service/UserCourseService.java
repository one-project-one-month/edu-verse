package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserCourseDTO;

import java.util.List;

public interface UserCourseService {
    UserCourseDTO createUserCourse(UserCourseDTO userCourseDTO);

    UserCourseDTO getUserCourseById(long id);

    List<UserCourseDTO> getAllUserCourses();

    List<UserCourseDTO> getUserCoursesByUserId(long userId);

    List<UserCourseDTO> getUserCoursesByCourseId(long courseId);

    UserCourseDTO updateUserCourse(UserCourseDTO userCourseDTO);

    void deleteUserCourse(long id);
}
