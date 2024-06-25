/**
 * @Author : Kyaw Zaw Htet @Date : 5/11/2024 @Time : 10:45 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.CourseDetailDto;
import java.util.List;

public interface CourseDetailService {

  List<CourseDetailDto> getAllCourseDetails();

  CourseDetailDto getCourseDetailById(Long id);

  CourseDetailDto createCourseDetail(CourseDetailDto courseDetailDto);

  CourseDetailDto updateCourseDetail(CourseDetailDto courseDetailDto);

  void deleteCourseDetail(Long id);

  List<CourseDetailDto> paginate(int pageNo, int limit);
}
