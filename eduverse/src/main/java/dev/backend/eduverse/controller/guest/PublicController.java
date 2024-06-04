package dev.backend.eduverse.controller.guest;

import java.util.ArrayList;
import java.util.List;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.CategoryDto;
import dev.backend.eduverse.service.AnnouncementService;
import dev.backend.eduverse.service.CategoryService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.dto.PathwayDTO;
import dev.backend.eduverse.service.CourseService;
import dev.backend.eduverse.service.PathwayService;
import dev.backend.eduverse.util.response_template.PageNumberResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/public/")
@RequiredArgsConstructor
public class PublicController {
    private final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    private final CourseService courseService;

    private final CategoryService categoryService;

    private final PathwayService pathwayService;

    private final AnnouncementService announcementService;

    @GetMapping("/courses")
    @Operation(summary = "Retrieve all courses", tags = { "Course Reader" })
    public ResponseEntity<ApiResponse<PageNumberResponse<List<CourseDTO>>>> readCourses(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return courseService.readCourseByPagniation(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No courses found",
                "Courses retrieved successfully");
    }

    @GetMapping("/course/")
    @Operation(summary = "Retrieve courses by name containing", tags = {"Course Reader"})
    public ResponseEntity<?> getCoursesByName(@RequestParam(value = "search") String name) {
        try {
            List<CourseDTO> courses = courseService.getCoursesByName(name);
            if (courses.isEmpty()) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "No courses found containing name: " + name,
                        new ArrayList<>());
            } else {
                return ResponseEntity.ok().body(courses);
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve courses by name", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve courses",
                    e.getMessage());
        }
    }

    @GetMapping("/category/")
    @Operation(summary = "Get all category", tags = {"Get all Category"})
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        try {
            List<CategoryDto> categories = categoryService.getAllCategory();
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, " Successfully get categoreis", categories);
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
        }
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get  category", tags = {"Get Category"})
    public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable Long categoryId) {
        try {
            CategoryDto categoryDto = categoryService.getCategory(categoryId);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Get Category with ID" + categoryId, categoryDto);
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
        }
    }

    @Operation(summary = "Get All Announcement", description = "Get All Announcement REST API is used to get all the Announcements from the database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    @GetMapping("/announcement/")
    public ResponseEntity<PageNumberResponse<AnnouncementDto>> getAllAnnouncements(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        List<AnnouncementDto> announcements = announcementService.paginate(pageNo, limit);

        return new ResponseEntity<>(new PageNumberResponse(pageNo, limit, announcements), HttpStatus.OK);
    }

    @GetMapping("/pathways")
    @Operation(summary = "Retrieve all pathways", tags = {"Pathway Reader"})
    public ResponseEntity<ApiResponse<PageNumberResponse<List<PathwayDTO>>>> readPathways(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        try {
            List<PathwayDTO> pathwayList = pathwayService.readPathwayByPagniation(pageNo, limit);
            if (pathwayList.isEmpty()) {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK,
                        "No pathways found",
                        new PageNumberResponse<>(pageNo, limit, pathwayList)
                );
            } else {
                return ResponseUtil.createSuccessResponse(
                        HttpStatus.OK,
                        "Pathways retrieved successfully",
                        new PageNumberResponse<>(pageNo, limit, pathwayList)
                );
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve pathways", e);
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to retrieve pathways",
                    null
            );
        }
    }
}
