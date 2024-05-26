package dev.backend.eduverse.controller.guest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.service.CourseService;
import dev.backend.eduverse.util.response_template.PageNumberResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/public/")
public class PublicController {
	 private final Logger logger = LoggerFactory.getLogger(PublicController.class);

	    @InitBinder
	    public void initBinder(WebDataBinder dataBinder) {
	        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    }

	    @Autowired
	    private final CourseService courseService;
	    
	    private final int PageSize = 10;

	    public PublicController(CourseService courseService) {
	        this.courseService = courseService;
	    }

	    @GetMapping("/course/page/{pageNumber}")
	    @Operation(
	            summary = "Retrieve all courses",
	            tags = {"Course Reader"})
	    public ResponseEntity<?> readCourses(@PathVariable int pageNumber) {
	        try {
	            List<CourseDTO> courseList = courseService.readCourseByPagniation(pageNumber, PageSize);
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
	    
	    @GetMapping("")
	    @Operation(
	            summary = "Retrieve courses by name containing",
	            tags = {"Course Reader"})
	    public ResponseEntity<?> getCoursesByName(@RequestParam(value = "search") String name) {
	        try {
	            List<CourseDTO> courses = courseService.getCoursesByName(name);
	            if (courses.isEmpty()) {
	                return ResponseUtil.createSuccessResponse(
	                        HttpStatus.OK, "No courses found containing name: " + name, new ArrayList<>());
	            } else {
	                return ResponseEntity.ok().body(courses);
	            }
	        } catch (Exception e) {
	            logger.error("Failed to retrieve courses by name", e);
	            return ResponseUtil.createErrorResponse(
	                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve courses", e.getMessage());
	        }
	    }  
}
