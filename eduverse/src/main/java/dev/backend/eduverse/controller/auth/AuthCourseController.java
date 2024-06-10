package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.CourseDto;
import dev.backend.eduverse.dto.UserCourseDto;
import dev.backend.eduverse.service.CourseService;
import dev.backend.eduverse.service.UserCourseService;
import dev.backend.eduverse.service.UserService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for Course")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthCourseController {
    private final UserService userService;
    private final UserCourseService userCourseService;
    private final CourseService courseService;
    private final Logger logger = LoggerFactory.getLogger(AuthCourseController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/course/{user_id}/registered-courses")
    public ResponseEntity<?> getAllRegisterCourses(@PathVariable Long user_id) {

        List<Object[]> courseDTOList = userService.getAllRegisteredCourses(user_id);
        return ResponseEntity.ok().body(courseDTOList);
    }

    @PostMapping("/course/enroll")
    public ResponseEntity<ApiResponse<UserCourseDto>> enroll(@Valid @RequestBody UserCourseDto userCourseDTO) {

        UserCourseDto savedUserCourse = userCourseService.createUserCourse(userCourseDTO);

        return ResponseEntity.ok()
                .body(
                        new ApiResponse<>(HttpStatus.OK, "Successfully Enrolled.", savedUserCourse)
                );
    }

    @PostMapping("/admin/course")
    @Operation(summary = "Create a new course")
    public ResponseEntity<ApiResponse<String>> createCourse(@Valid @RequestBody CourseDto courseDTO) {
        try {
            boolean created = courseService.createCourse(courseDTO);
            if (created) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Course created successfully", "created");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create course",
                        "Creation failed due to unknown reasons");
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create course", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to create course", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create course",
                    e.getMessage());
        }
    }

    @PutMapping("/admin/course/{courseId}")
    @Operation(summary = "Update a course's information")
    public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Long courseId,
                                                            @Valid @RequestBody CourseDto courseDTO) {
        try {
            boolean updated = courseService.updateCourse(courseDTO, courseId);
            if (updated) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Course updated successfully", "updated");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId,
                        null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId,
                    e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update course",
                    e.getMessage());
        }
    }

    @DeleteMapping("/admin/course/{courseId}")
    @Operation(summary = "Delete a course by ID")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Long courseId) {
        try {
            boolean deleted = courseService.deleteCourse(courseId);
            if (deleted) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Course deleted successfully", "deleted");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Course not found with ID: " + courseId,
                        null);
            }
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete course",
                    e.getMessage());
        }
    }
}
