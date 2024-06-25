/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.repository.AdminRepository;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private final CourseRepository courseRepository;

	@Autowired
	private final AdminRepository adminRepository;

	private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	private final ModelMapper modelMapper;

	public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper,
			AdminRepository adminRepository) {
		this.courseRepository = courseRepository;
		this.modelMapper = modelMapper;
		this.adminRepository = adminRepository;
	}

	@Override
	public boolean createCourse(CourseDTO courseDTO) {
		logger.info("Entering the creation process");
		courseDTO.setCreatedAt(LocalDate.now());
		Admin admin = adminRepository.findById(courseDTO.getAdminId())
				.orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: " + courseDTO.getAdminId()));
		Course course = modelMapper.map(courseDTO, Course.class);
		course.setAdmin(admin);
		try {
			courseRepository.save(course);
			logger.info("Course created successfully");
			return true; // Creation successfully
		} catch (Exception e) {
			return false; // Creation failed
		}
	}

	@Override
	public List<CourseDTO> getAllCourse() {
		logger.info("Entering the get all course process");

		try {
			List<Course> courses = courseRepository.findAll();
			int numberOfCourses = courses.size(); // Get the number of retrieved course list
			logger.info("Retrieved {} courses", numberOfCourses);
			List<CourseDTO> courseDTOs = courses.stream().map(course -> modelMapper.map(course, CourseDTO.class))
					.collect(Collectors.toList());
			return courseDTOs;
		} catch (Exception e) {
			logger.error("Error occurred while retrieving course list", e);
			throw new RuntimeException("Failed to retrieve course list", e);
		}
	}

	@Override
	public CourseDTO getCourseByID(Long id) {
		logger.info("Retrieving course by ID: {}", id);
		Optional<Course> courseOptional = courseRepository.findById(id);
		Course course = courseOptional
				.orElseThrow(() -> new EntityNotFoundException("Entity is not found with this id" + id));
		return modelMapper.map(course, CourseDTO.class);
	}

	@Override
	public boolean updateCourse(CourseDTO courseDTO, Long id) {
		logger.info("Updating course with ID: {}", id);

		// Retrieve existing course from the repository
		Course existingCourse = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

		modelMapper.map(courseDTO, existingCourse);

		try {
			courseRepository.save(existingCourse);
			logger.info("Course updated successfully");
			return true; // Update process successful
		} catch (Exception e) {
			logger.error("Failed to update course with ID: {}", id, e);
			return false; // Update process failed
		}
	}

	@Override
	public boolean deleteCourse(Long id) {
		logger.info("Deleting course with ID: {}", id);

		Optional<Course> courseOptional = courseRepository.findById(id);
		if (courseOptional.isEmpty()) {
			logger.warn("Course not found with ID: {}", id);
			return false;
		}

		try {
			courseRepository.deleteById(id);
			logger.info("Course deleted successfully");
			return true; // Deletion successful
		} catch (Exception e) {
			logger.error("Failed to delete course with ID: {}", id, e);
			return false; // Deletion failed
		}
	}

	@Override
	public List<CourseDTO> readCourseByPagination(int pageNumber, int pageSize) throws IllegalAccessException {
		if (pageNumber < 1 || pageSize < 1) {
			throw new IllegalAccessException("Cannot access the page number less than one");
		}

		int offset = (pageNumber - 1) * pageSize;
		try {
			List<Course> courseList = courseRepository.paginate(pageSize, offset);
			return courseList.stream().map(course -> modelMapper.map(course, CourseDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw e;
		}
	}
}
