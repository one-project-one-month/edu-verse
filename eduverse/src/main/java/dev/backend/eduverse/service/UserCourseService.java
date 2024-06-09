package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserCourseDto;

import java.util.List;

public interface UserCourseService {
    UserCourseDto createUserCourse(UserCourseDto userCourseDTO);

    UserCourseDto getUserCourseById(long id);

    List<UserCourseDto> getAllUserCourses();

    List<UserCourseDto> getUserCoursesByUserId(long userId);

    List<UserCourseDto> getUserCoursesByCourseId(long courseId);

    UserCourseDto updateUserCourse(UserCourseDto userCourseDTO);

    void deleteUserCourse(long id);
}
