/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.CourseDto;
import dev.backend.eduverse.model.Admin;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.repository.AdminRepository;
import dev.backend.eduverse.repository.CourseRepository;
import dev.backend.eduverse.service.CourseService;
import jakarta.persistence.EntityNotFoundException;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.backend.eduverse.exception.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private final AdminRepository adminRepository;

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final ModelMapper modelMapper;

    @Value("${image-storage-directory}")
    private String imageStorageDirectory;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper,
                             AdminRepository adminRepository) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean createCourse(CourseDto courseDTO) {
        logger.info("Entering the creation process");

        courseDTO.setCreatedAt(LocalDate.now());
        Admin admin = adminRepository.findById(courseDTO.getAdminId())
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: " + courseDTO.getAdminId()));

        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setLevel(courseDTO.getLevel());
        course.setDuration(courseDTO.getDuration());
        course.setShortDescription(courseDTO.getShortDescription());
        course.setLongDescription(courseDTO.getLongDescription());
        course.setCreatedAt(courseDTO.getCreatedAt());
        course.setStatus(courseDTO.getStatus());
        course.setAdmin(admin);

        try {
            //image uploading
            // create directory if it does not exist
            if (courseDTO.getImage() != null) {
                Path imageStoragePath = Paths.get(imageStorageDirectory);
                if (!Files.exists(imageStoragePath)) {
                    Files.createDirectories(imageStoragePath);
                }
                //decode the 64base string file
                byte[] decodedBytes = Base64.getDecoder().decode(courseDTO.getImage());
                String imageName = new Date().getTime() + ".png";
                Path toStorePath = imageStoragePath.resolve(imageName);

                //write file in static/images folder
                Files.write(toStorePath, decodedBytes);

                //generate image url
                String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/")
                        .path(imageName).toUriString();

                course.setImage(imageUrl);
            }

            courseRepository.save(course);
            logger.info("Course created successfully");
            return true; // Creation successfully
        } catch (Exception e) {
            return false; // Creation failed
        }
    }

    @Override
    @Cacheable(value = "course")
    public List<CourseDto> getAllCourse() {
        logger.info("Entering the get all course process");

        try {
            List<Course> courses = courseRepository.findAll();
            int numberOfCourses = courses.size(); // Get the number of retrieved course list
            logger.info("Retrieved {} courses", numberOfCourses);
            return courses.stream().map(course -> modelMapper.map(course, CourseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred while retrieving course list", e);
            throw new RuntimeException("Failed to retrieve course list", e);
        }
    }

    @Override
    @Cacheable(value = "course", key = "#id")
    public CourseDto getCourseByID(Long id) {
        logger.info("Retrieving course by ID: {}", id);
        Optional<Course> courseOptional = courseRepository.findById(id);
        Course course = courseOptional
                .orElseThrow(() -> new EntityNotFoundException("Entity is not found with this id" + id));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    @CachePut(value = "course", key = "#course.id")
    public boolean updateCourse(CourseDto courseDTO, Long id) {
        logger.info("Updating course with ID: {}", id);

        // Retrieve existing course from the repository
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        String oldImageUrl = existingCourse.getImage();
        modelMapper.map(courseDTO, existingCourse);

        try {
            //delete existing image and upload new image
            if (courseDTO.getImage() != null) {
                //delete associated image
                //get file name from url
                String fileName = Paths.get(new URI(oldImageUrl).getPath())
                        .getFileName().toString();
                Path toDeletePath = Paths.get(imageStorageDirectory).resolve(fileName);
                Files.deleteIfExists(toDeletePath);

                //upload new image
                //decode the 64base string file
                byte[] decodedBytes = Base64.getDecoder().decode(courseDTO.getImage());
                String imageName = new Date().getTime() + ".png";
                Path toStorePath = Paths.get(imageStorageDirectory).resolve(imageName);
                //write file in static/images folder
                Files.write(toStorePath, decodedBytes);

                //generate image url
                String newImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/images/")
                        .path(imageName).toUriString();

                existingCourse.setImage(newImageUrl);
            }

            courseRepository.save(existingCourse);
            logger.info("Course updated successfully");
            return true; // Update process successful
        } catch (Exception e) {
            logger.error("Failed to update course with ID: {}", id, e);
            return false; // Update process failed
        }
    }

    @Override
    @CacheEvict(value = "course", allEntries = true)
    public boolean deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);

        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            logger.warn("Course not found with ID: {}", id);
            return false;
        }

        try {
            Course toDeleteCourse = courseOptional.get();

            if (toDeleteCourse.getImage() != null) {
                //delete associated image
                String imageUrl = toDeleteCourse.getImage();
                //get file name from url
                String fileName = Paths.get(new URI(imageUrl).getPath())
                        .getFileName().toString();

                Path toDeleteImagePath = Paths.get(imageStorageDirectory).resolve(fileName);
                Files.deleteIfExists(toDeleteImagePath);
            }

            courseRepository.delete(toDeleteCourse);
            logger.info("Course deleted successfully");
            return true; // Deletion successful
        } catch (Exception e) {
            logger.error("Failed to delete course with ID: {}", id, e);
            return false; // Deletion failed
        }
    }

    @Override
    @Cacheable(value = "courseByPage", key = "#pageNumber")
    public List<CourseDto> readCourseByPagination(int pageNumber, int pageSize) {
        pageNumber = Math.max(pageNumber, 1);
        pageSize = (pageSize < 1) ? 10 : pageSize;

        int offset = (pageNumber - 1) * pageSize;
        try {
            List<Course> courseList = courseRepository.findAllWithDetailsPaginated(pageSize, offset);
            return courseList.stream()
                    .map(course -> modelMapper.map(course, CourseDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Failed to retrieve courses with pagination", e);
            throw new ServiceException("An error occurred while retrieving courses", e);
        }
    }

    @Override
    @Cacheable(value = "courseByName", key = "#name")
    public CourseDto getCourseByName(String name) {
        logger.info("Retrieving course by name: {}", name);
        Course course = courseRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with name: " + name));
        return modelMapper.map(course, CourseDto.class);
    }

    @Override
    @Cacheable(value = "coursesByName", key = "#name")
    public List<CourseDto> getCoursesByName(String name) {
        logger.info("Retrieving courses by name containing: {}", name);
        List<Course> courses = courseRepository.findByNameContaining(name);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }
}
