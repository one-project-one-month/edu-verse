/*
 * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.service.CourseService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.PageNumberResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Course",
        description = "CRUD REST APIs - Create Course, Update Course, Get All Courses, Delete Course")
@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @Autowired
    private final CourseService courseService;
    
    private final int PageSize = 10;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    @Operation(
            summary = "Create a new course",
            tags = {"Course Creator"})
    public ResponseEntity<ApiResponse<String>> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        try {
            boolean created = courseService.createCourse(courseDTO);
            if (created) {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK, "Course created successfully", "created");
            } else {
                return ResponseUtil.createErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        "Failed to create course",
                        "Creation failed due to unknown reasons");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.createErrorResponse(
                    HttpStatus.BAD_REQUEST, "Failed to create course", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to create course", e);
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create course", e.getMessage());
        }
    }

    @GetMapping("/page/{pageNumber}")
    @Operation(
            summary = "Retrieve all courses",
            tags = {"Course Reader"})
    public ResponseEntity<?> readCourses(@PathVariable int pageNumber) {
        try {
            List<CourseDTO> courseList = courseService.readCourseByPagination(pageNumber, PageSize);
            if (courseList.isEmpty()) {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK, "No courses found", new ArrayList<>());
            } else {
            	PageNumberResponse<List<CourseDTO>> response = new PageNumberResponse<>(pageNumber, PageSize, courseList);
                return ResponseEntity.ok().body(response);                
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve courses", e);
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve courses", null);
        }
    }

    @PutMapping("/{courseId}")
    @Operation(
            summary = "Update a course's information",
            tags = {"Update Course"})
    public ResponseEntity<ApiResponse<String>> updateCourse(
            @PathVariable Long courseId, @Valid @RequestBody CourseDTO courseDTO) {
        try {
            boolean updated = courseService.updateCourse(courseDTO, courseId);
            if (updated) {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK, "Course updated successfully", "updated");
            } else {
                return ResponseUtil.createErrorResponse(
                        HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId, null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseUtil.createErrorResponse(
                    HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId, e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update course", e.getMessage());
        }
    }

    @DeleteMapping("/{courseId}")
    @Operation(
            summary = "Delete a course by ID",
            tags = {"Delete Course By Id"})
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long courseId) {
        try {
            boolean deleted = courseService.deleteCourse(courseId);
            if (deleted) {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK, "Course deleted successfully", "deleted");
            } else {
                return ResponseUtil.createErrorResponse(
                        HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId, null);
            }
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete course", e.getMessage());
        }
    }
}
