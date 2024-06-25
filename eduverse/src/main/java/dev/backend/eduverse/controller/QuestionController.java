///*
// * @Author : Alvin
// * @Date : 6/9/2024
// * @Time : 10:00 PM
// * @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.QuestionDto;
//import dev.backend.eduverse.service.QuestionService;
//import dev.backend.eduverse.util.response_template.ApiResponse;
//import dev.backend.eduverse.util.response_template.PageNumberResponse;
//import dev.backend.eduverse.util.response_template.ResponseUtil;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Tag(name = "CRUD REST APIs for Question")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/question")
//public class QuestionController {
//    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);
//
//    private final QuestionService questionService;
//
//    @Operation(
//            summary = "Create Question",
//            description = "Create Question REST API is used to save Question in a database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
//    @PostMapping("/")
//    public ResponseEntity<ApiResponse<QuestionDto>> createQuestion(
//            @Valid @RequestBody QuestionDto question) {
//        try {
//            QuestionDto createdQuestion = questionService.createQuestion(question);
//            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Question created successfully", createdQuestion);
//        } catch (Exception e) {
//            logger.error("Failed to create question", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create question", null);
//        }
//    }
//
//    @Operation(
//            summary = "Get All Question",
//            description =
//                    "Get All Question REST API is used to get all the Questions from the database"
//            )
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @GetMapping("")
//    public ResponseEntity<ApiResponse<PageNumberResponse<List<QuestionDto>>>> getAllQuestions(
//            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
//            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
//    ) {
//        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
//                paginationParams -> {
//                    try {
//                        return questionService.readQuestionByPagination(paginationParams.pageNo(), paginationParams.limit());
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
//                },
//                logger,
//                "No questions found",
//                "Questions retrieved successfully");
//    }
//
//    @Operation(
//            summary = "Update Question",
//            description =
//                    "Update Question REST API is used to update a particular Question in the database"
//            )
//    @PutMapping("{id}")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    public ResponseEntity<ApiResponse<QuestionDto>> updateQuestion(
//            @PathVariable("id") Long id, @Valid @RequestBody QuestionDto question) {
//        try {
//            QuestionDto updatedQuestion = questionService.updateQuestion(id, question);
//            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Question updated successfully", updatedQuestion);
//        } catch (Exception e) {
//            logger.error("Failed to update question", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update question", null);
//        }
//    }
//
//    @Operation(
//            summary = "Delete Question",
//            description =
//                "Delete Question REST API is used to delete a particular Question from the database"
//            )
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {
//
//        questionService.deleteQuestion(id);
//        return new ResponseEntity<>("Question successfully deleted!", HttpStatus.OK);
//    }
//}
