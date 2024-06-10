package dev.backend.eduverse.controller.guest;

import java.util.ArrayList;
import java.util.List;

import dev.backend.eduverse.dto.*;
import dev.backend.eduverse.exception.EntityNotFoundException;
import dev.backend.eduverse.service.*;
import dev.backend.eduverse.util.response_template.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import dev.backend.eduverse.util.response_template.PageNumberResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "CRUD REST APIs for Public", description = "Public routes")
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

    private final AnswerService answerService;

    private final QuestionService questionService;

    private final ExamService examService;

    private final ModuleService moduleService;

    @GetMapping("/courses")
    @Operation(summary = "Retrieve all courses")
    public ResponseEntity<ApiResponse<PageNumberResponse<List<CourseDto>>>> readCourses(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return courseService.readCourseByPagination(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No courses found",
                "Courses retrieved successfully");
    }

    @GetMapping("/course/")
    @Operation(summary = "Retrieve courses by name containing")
    public ResponseEntity<?> getCoursesByName(@RequestParam(value = "search") String name) {
        try {
            List<CourseDto> courses = courseService.getCoursesByName(name);
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
    @Operation(summary = "Get all category")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        try {
            List<CategoryDto> categories = categoryService.getAllCategory();
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, " Successfully get categoreis", categories);
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
        }
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get  category")
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
    @Operation(summary = "Retrieve all pathways")
    public ResponseEntity<ApiResponse<PageNumberResponse<List<PathwayDto>>>> readPathways(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return pathwayService.readPathwayByPagination(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No pathways found",
                "Pathways retrieved successfully");
    }

    // Answer operations
    @Operation(summary = "Get All Answers", description = "Get All Answers REST API is used to retrieve all answers from the database")
    @GetMapping("/answer")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<?> getAllAnswers() {
        List<AnswerDto> allAnswers = answerService.getAllAnswers();
        if (allAnswers == null || allAnswers.isEmpty()) {
            return ResponseUtil.createErrorResponse(HttpStatus.OK, "No Answers found", "No answers found");
        }
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "All Answers retrieved successfully", allAnswers);
    }

    @Operation(summary = "Get Answer by ID", description = "Get Answer by ID REST API is used to retrieve a specific answer by its ID from the database")
    @GetMapping("/answer/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<?> getAnswerById(@PathVariable("id") Long id) {
        try {
            AnswerDto answer = answerService.getAnswerById(id);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Answer retrieved successfully", answer);
        } catch (EntityNotFoundException e) {
            logger.error("Failed to retrieve answer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.OK, "Failed to retrieve answer", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to retrieve answer", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve answer", null);
        }
    }

    @Operation(summary = "Get Exam by ID", description = "Get Exam by ID REST API is used to retrieve a specific exam by its ID from the database")
    @GetMapping("/exam/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<?> getExamById(@PathVariable("id") Long id) {
        try {
            ExamDto exam = examService.getExamById(id);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Exam retrieved successfully", exam);
        } catch (EntityNotFoundException e) {
            logger.error("Failed to retrieve exam", e);
            return ResponseUtil.createErrorResponse(HttpStatus.OK, "Failed to retrieve exam", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to retrieve exam", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve exam", null);
        }
    }

    @Operation(summary = "Get Module by ID", description = "Get Module by ID REST API is used to retrieve a specific module by its ID from the database")
    @GetMapping("/module/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<?> getModuleById(@PathVariable("id") Long id) {
        try {
            ModuleDto module = moduleService.getById(id);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module retrieved successfully", module);
        } catch (EntityNotFoundException e) {
            logger.error("Failed to retrieve module", e);
            return ResponseUtil.createErrorResponse(HttpStatus.OK, "Failed to retrieve module", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to retrieve module", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve module", null);
        }
    }

    // Question operations
    @Operation(
            summary = "Get All Question",
            description =
                    "Get All Question REST API is used to get all the Questions from the database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    @GetMapping("/question")
    public ResponseEntity<ApiResponse<PageNumberResponse<List<QuestionDto>>>> getAllQuestions(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return questionService.readQuestionByPagination(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No questions found",
                "Questions retrieved successfully");
    }

    // Module
    @Operation(
            summary = "Get All Module",
            description =
                    "Get All Module REST API is used to get all the Modules from the database"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    @GetMapping("")
    public ResponseEntity<ApiResponse<PageNumberResponse<List<ModuleDto>>>> getAllModules(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {
        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
                paginationParams -> {
                    try {
                        return moduleService.getAllModulesByPagination(paginationParams.pageNo(), paginationParams.limit());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                },
                logger,
                "No modules found",
                "Modules retrieved successfully");
    }
}
