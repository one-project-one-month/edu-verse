/**
 * @Author : Kyaw Zaw Htet @Date : 5/11/2024 @Time : 10:45 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.CourseDetailDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Announcement;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.model.CourseDetail;
import dev.backend.eduverse.repository.AdminRepository;
import dev.backend.eduverse.repository.CourseDetailRepository;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.service.CourseDetailService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseDetailServiceImpl implements CourseDetailService {

  private CourseDetailRepository courseDetailRepository;

  private CourseRepository courseRepository;

  private AdminRepository adminRepository;

  private ModelMapper modelMapper;

  @Override
  public List<CourseDetailDto> getAllCourseDetails() {

    List<CourseDetail> courseDetails = courseDetailRepository.findAll();
    return courseDetails.stream()
        .map((courseDetail -> modelMapper.map(courseDetail, CourseDetailDto.class)))
        .collect(Collectors.toList());
  }

  @Override
  public CourseDetailDto getCourseDetailById(Long id) {

    CourseDetail courseDetail =
        courseDetailRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CourseDetail", "id", id));
    return modelMapper.map(courseDetail, CourseDetailDto.class);
  }

  @Override
  public CourseDetailDto createCourseDetail(CourseDetailDto courseDetailDto) {

    Course course = courseRepository.findById(courseDetailDto.getCourseId()).orElseThrow();
    Admin admin = adminRepository.findById(courseDetailDto.getAdminId()).orElseThrow();

    CourseDetail courseDetail = modelMapper.map(courseDetailDto, CourseDetail.class);
    courseDetail.setCourse(course);
    courseDetail.setAdmin(admin);
    CourseDetail savedCourseDetail = courseDetailRepository.save(courseDetail);
    CourseDetailDto savedCourseDetailDto =
        modelMapper.map(savedCourseDetail, CourseDetailDto.class);
    return savedCourseDetailDto;
  }

  @Override
  public CourseDetailDto updateCourseDetail(CourseDetailDto courseDetailDto) {

    CourseDetail exitingCourseDetail =
        courseDetailRepository
            .findById(courseDetailDto.getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("CourseDetail", "id", courseDetailDto.getId()));

    Course exitingCourse =
        courseRepository
            .findById(courseDetailDto.getCourseId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseDetailDto.getCourseId()));

    Admin exitingAdmin =
        adminRepository
            .findById(courseDetailDto.getAdminId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Admin", "id", courseDetailDto.getAdminId()));

    exitingCourseDetail.setTitle(courseDetailDto.getTitle());
    exitingCourseDetail.setContent(courseDetailDto.getContent());
    exitingCourseDetail.setCourse(exitingCourse);
    exitingCourseDetail.setAdmin(exitingAdmin);

    CourseDetail updatedCourseDetail = courseDetailRepository.save(exitingCourseDetail);

    return modelMapper.map(updatedCourseDetail, CourseDetailDto.class);
  }

  @Override
  public void deleteCourseDetail(Long id) {

    CourseDetail exitingCourseDetail =
        courseDetailRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CourseDetail", "id", id));

    courseDetailRepository.deleteById(exitingCourseDetail.getId());
  }

  @Override
  public List<CourseDetailDto> paginate(int pageNo, int limit) {
    //if provided page number is less than 1, 1 will be the default pageNO
    pageNo = Math.max(pageNo, 1);

    //if provided size limit is less than 1, 10 will be the default size limit
    limit = (limit < 1) ? 10 : limit;

    int offset = (pageNo - 1) * limit;

    List<CourseDetail> courseDetailList = courseDetailRepository.paginate(limit, offset);

    return courseDetailList.stream()
            .map(courseDetail -> modelMapper.map(courseDetail, CourseDetailDto.class))
            .collect(Collectors.toList());
  }
}
