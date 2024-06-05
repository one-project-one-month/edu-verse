package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.userCourseDTO;
import dev.backend.eduverse.model.UserCourse;
import dev.backend.eduverse.repository.userCourseRepository;
import dev.backend.eduverse.service.userCourseServices;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userCourseServiceImpl implements userCourseServices {

  @Autowired private userCourseRepository userCourseRepository;

  // presentation>data>map>save
  @Override
  public userCourseDTO createUserCourse(userCourseDTO userCourseDTO) {
    UserCourse userCourse = mapDtoToEntity(userCourseDTO);
    userCourse = userCourseRepository.save(userCourse);
    return mapEntityToDto(userCourse);
  }

  @Override
  public userCourseDTO getUserCourseById(long id) {
    UserCourse userCourse = userCourseRepository.findById(id).orElse(null);
    if (userCourse != null) {
      return mapEntityToDto(userCourse);
    }
    return null;
  }

  @Override
  public List<userCourseDTO> getAllUserCourses() {
    List<UserCourse> userCourses = userCourseRepository.findAll();
    return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
  }

  @Override
  public List<userCourseDTO> getUserCoursesByUserId(long userId) {
    List<UserCourse> userCourses = userCourseRepository.findByUserId(userId);
    return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
  }

  @Override
  public List<userCourseDTO> getUserCoursesByCourseId(long courseId) {
    List<UserCourse> userCourses = userCourseRepository.findByCourseId(courseId);
    return userCourses.stream().map(this::mapEntityToDto).collect(Collectors.toList());
  }

  @Override
  public userCourseDTO updateUserCourse(userCourseDTO userCourseDTO) {
    UserCourse userCourse = mapDtoToEntity(userCourseDTO);
    userCourse = userCourseRepository.save(userCourse);
    return mapEntityToDto(userCourse);
  }

  @Override
  public void deleteUserCourse(long id) {
    userCourseRepository.deleteById(id);
  }

  private UserCourse mapDtoToEntity(userCourseDTO userCourseDTO) {
    UserCourse userCourse = new UserCourse();
    userCourse.setId(userCourseDTO.getId());
    userCourse.setCreatedDate(userCourseDTO.getCreatedDate());
    // Similarly, set user and course properties
    return userCourse;
  }

  private userCourseDTO mapEntityToDto(UserCourse userCourse) {
    userCourseDTO userCourseDTO = new userCourseDTO();
    userCourseDTO.setId(userCourse.getId());
    userCourseDTO.setCreatedDate(userCourse.getCreatedDate());
    // Similarly, set userId and courseId properties
    return userCourseDTO;
  }
}
