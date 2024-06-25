/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.CourseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
  boolean createCourse(CourseDto courseDTO);

  List<CourseDto> getAllCourse();

  CourseDto getCourseByID(Long id);

  boolean updateCourse(CourseDto courseDTO, Long id);

  boolean deleteCourse(Long id);

  List<CourseDto> readCourseByPagination(int pageNumber, int pageSize) throws IllegalAccessException;

  CourseDto getCourseByName(String name);
  
  List<CourseDto> getCoursesByName(String name);
}
