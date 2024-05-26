/* * @Author : Alvin
 * @Date : 5/11/2024
 * @Time : 9:00 PM
 * @Project_Name : eduverse
 */

package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.CategoryDto;
import dev.backend.eduverse.service.AnnouncementService;
import dev.backend.eduverse.service.impl.CategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.dto.PathwayDTO;
import dev.backend.eduverse.exception.NameAlreadyExistException;
import dev.backend.eduverse.service.CourseService;
import dev.backend.eduverse.service.PathwayService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Tag(name = "CRUD REST APIs for Course", description = "CRUD REST APIs - Create Course, Update Course, Get All Courses, Delete Course")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	private final CourseService courseService;

	private final CategoryServiceImpl categoryService;

	private final AnnouncementService announcementService;

	private final int PageSize = 10;

	public AuthController(CourseService courseService, CategoryServiceImpl categoryService,
			AnnouncementService announcementService) {
		super();
		this.courseService = courseService;
		this.categoryService = categoryService;
		this.announcementService = announcementService;
	}

	@PostMapping("course/")
	@Operation(summary = "Create a new course", tags = { "Course Creator" })
	public ResponseEntity<ApiResponse<String>> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
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

	@PutMapping("/course/{courseId}")
	@Operation(summary = "Update a course's information", tags = { "Update Course" })
	public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Long courseId,
			@Valid @RequestBody CourseDTO courseDTO) {
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

	@DeleteMapping("/course/{courseId}")
	@Operation(summary = "Delete a course by ID", tags = { "Delete Course By Id" })
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

	@PostMapping("/category")
	@Operation(summary = "Create new category", tags = { "Create Category" })
	public ResponseEntity<ApiResponse<String>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		try {
			categoryService.createNewCategory(categoryDto);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Category Created successfully",
					categoryDto.toString());
		} catch (DataIntegrityViolationException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create category",
					e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create category",
					e.getMessage());
		}
	}

	@GetMapping("/category/{categoryId}")
	@Operation(summary = "Get  category", tags = { "Get Category" })
	public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable Long categoryId) {
		try {
			CategoryDto categoryDto = categoryService.getCategory(categoryId);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Get Category with ID" + categoryId, categoryDto);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
		}
	}

	@DeleteMapping("/category/{categoryId}")
	@Operation(summary = "Delete category by id ", tags = { "Delete Category by ID" })
	public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
		try {
			categoryService.deleteCategory(categoryId);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Category Deleted successfully",
					"Id " + categoryId);
		} catch (EmptyResultDataAccessException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Category not found", null);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Category",
					e.getMessage());
		}
	}

	@Operation(summary = "Create Announcement", description = "Create Announcement REST API is used to save Announcement in a database")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
	@PostMapping("/announcement")
	public ResponseEntity<AnnouncementDto> createAnnouncement(@Valid @RequestBody AnnouncementDto announcement) {

		AnnouncementDto createAnnouncement = announcementService.createAnnouncement(announcement);
		return new ResponseEntity<>(createAnnouncement, HttpStatus.CREATED);
	}

}
