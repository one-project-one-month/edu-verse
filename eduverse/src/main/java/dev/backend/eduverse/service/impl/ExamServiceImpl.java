/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/4/2024
 * @Time : 7:56 AM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;


import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.ExamDto;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.exception.ServiceException;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Announcement;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.model.Exam;
import dev.backend.eduverse.repository.AdminRepository;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.repository.ExamRepository;
import dev.backend.eduverse.service.ExamService;
import dev.backend.eduverse.util.response_template.EntityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {

    private ExamRepository examRepository;

    private CourseRepository courseRepository;

    private AdminRepository adminRepository;

    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

    @Override
    public ExamDto createExam(ExamDto examDto) {
        logger.info("Entering the creation process");
        Exam exam = modelMapper.map(examDto, Exam.class);
        Course course = EntityUtil.getEntityById(courseRepository, examDto.getCourseId(), "Course");
        Admin admin = EntityUtil.getEntityById(adminRepository, examDto.getAdminId(), "Admin");
        exam.setCourse(course);
        exam.setAdmin(admin);
        Exam savedExam = EntityUtil.saveEntity(examRepository, exam, "exam");
        return modelMapper.map(savedExam, ExamDto.class);
    }

    @Override
    public ExamDto getExamById(Long id) {
        Exam exam =
                examRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", id));
        return modelMapper.map(exam, ExamDto.class);
    }

    @Override
    public List<ExamDto> readExamByPagination(int pageNo, int limit) throws IllegalAccessException {
        logger.info("Entering the get all exam process with pagination");
        pageNo = Math.max(pageNo, 1);
        limit = (limit < 1) ? 10 : limit;

        int offset = (pageNo - 1) * limit;
        try {
            List<Exam> examList = examRepository.paginate(limit, offset);
            return examList.stream()
                    .map(exam -> modelMapper.map(exam, ExamDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to retrieve exams with pagination", e);
            throw new ServiceException("An error occurred while retrieving exams", e);
        }
    }

    @Override
    public ExamDto updateExam(Long id, ExamDto examDto) {
        Exam exitingExam =
                examRepository
                        .findById(examDto.getId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Exam", "id", examDto.getId()));

        Course exitingCourse =
                courseRepository
                        .findById(examDto.getCourseId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Course", "id", examDto.getCourseId()));
        Admin exitingAdmin =
                adminRepository
                        .findById(examDto.getAdminId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Admin", "id", examDto.getAdminId()));

        exitingExam.setExamName(examDto.getExamName());
        exitingExam.setCreatedAt(examDto.getCreatedAt());
        exitingExam.setCourse(exitingCourse);
        exitingExam.setAdmin(exitingAdmin);

        Exam updatedExam = examRepository.save(exitingExam);

        return modelMapper.map(updatedExam, ExamDto.class);
    }

    @Override
    public void deleteExam(Long id) {
        Exam exitingExam =
                examRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exam", "id", id));
        examRepository.deleteById(exitingExam.getId());
    }
}
