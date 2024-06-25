package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.UserCourseDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.model.UserCourse;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.repository.UserRepository;
import dev.backend.eduverse.repository.UserCourseRepository;
import dev.backend.eduverse.service.UserCourseService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCourseServiceImpl implements UserCourseService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private final UserCourseRepository userCourseRepository;

    // presentation>data>map>save/
    @Override
    public UserCourseDto createUserCourse(UserCourseDto userCourseDTO) {
        User user = userRepository
                .findById(userCourseDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userCourseDTO.getUserId()));

        Course course = courseRepository
                .findById(userCourseDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", userCourseDTO.getCourseId()));

        UserCourse userCourse = userCourseRepository
                .findByUserIdAndCourseId(userCourseDTO.getUserId(), userCourseDTO.getCourseId());

        if (userCourse != null) {
            throw new RuntimeException("User has already enrolled this course!");
        }

        UserCourse savedUserCourse = userCourseRepository.save(
                modelMapper.map(userCourseDTO, UserCourse.class)
        );

        return modelMapper.map(savedUserCourse, UserCourseDto.class);
    }

    @Override
    public UserCourseDto getUserCourseById(long id) {
        UserCourse userCourse = userCourseRepository.findById(id).orElse(null);
        if (userCourse != null) {
            return mapEntityToDto(userCourse);
        }
        return null;
    }

    @Override
    public List<UserCourseDto> getAllUserCourses() {
        List<UserCourse> userCourses = userCourseRepository.findAll();
        return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserCourseDto> getUserCoursesByUserId(long userId) {
        List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);
        return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserCourseDto> getUserCoursesByCourseId(long courseId) {
        List<UserCourse> userCourses = userCourseRepository.findByCourseId(courseId);
        return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserCourseDto updateUserCourse(UserCourseDto userCourseDTO) {
        UserCourse userCourse = mapDtoToEntity(userCourseDTO);
        userCourse = userCourseRepository.save(userCourse);
        return mapEntityToDto(userCourse);
    }

    @Override
    public void deleteUserCourse(long id) {
        userCourseRepository.deleteById(id);
    }

    private UserCourse mapDtoToEntity(UserCourseDto userCourseDTO) {
        UserCourse userCourse = new UserCourse();
        userCourse.setId(userCourseDTO.getId());
        userCourse.setCreatedDate(userCourseDTO.getCreatedDate());
        // Similarly, set user and course properties
        return userCourse;
    }

    private UserCourseDto mapEntityToDto(UserCourse userCourse) {
        UserCourseDto userCourseDTO = new UserCourseDto();
        userCourseDTO.setId(userCourse.getId());
        userCourseDTO.setCreatedDate(userCourse.getCreatedDate());
        // Similarly, set userId and courseId properties
        return userCourseDTO;
    }
}
