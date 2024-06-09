///*
// * @Author : Alvin
// * @Date : 6/9/2024
// * @Time : 10:00 PM
// * @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.AnswerDto;
//import dev.backend.eduverse.service.AnswerService;
//import dev.backend.eduverse.util.response_template.ApiResponse;
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
//@Tag(name = "CRUD REST APIs for Answer")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/answer")
//public class AnswerController {
//
//    private final Logger logger = LoggerFactory.getLogger(AnswerController.class);
//    private final AnswerService answerService;
//
//    // Create operation
//    @Operation(summary = "Create Answer", description = "Create Answer REST API is used to save Answer in a database")
//    @PostMapping("/")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
//    public ResponseEntity<?> createAnswer(@Valid @RequestBody AnswerDto answerDto) {
//        try {
//            AnswerDto createdAnswer = answerService.createAnswer(answerDto);
//            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Answer created successfully", createdAnswer);
//        } catch (Exception e) {
//            logger.error("Failed to create answer", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
//        }
//    }
//
//    // Read operations
//    @Operation(summary = "Get All Answers", description = "Get All Answers REST API is used to retrieve all answers from the database")
//    @GetMapping("")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    public ResponseEntity<?> getAllAnswers() {
//        List<AnswerDto> allAnswers = answerService.getAllAnswers();
//        if (allAnswers == null || allAnswers.isEmpty()) {
//            return ResponseUtil.createErrorResponse(HttpStatus.OK, "No Answers found", "No answers found");
//        }
//        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "All Answers retrieved successfully", allAnswers);
//    }
//
//    @Operation(summary = "Get Answer by ID", description = "Get Answer by ID REST API is used to retrieve a specific answer by its ID from the database")
//    @GetMapping("/{id}")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    public ResponseEntity<ApiResponse<AnswerDto>> getAnswerById(@PathVariable("id") Long id) {
//        try {
//            AnswerDto answer = answerService.getAnswerById(id);
//            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Answer retrieved successfully", answer);
//        } catch (Exception e) {
//            logger.error("Failed to retrieve answer", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve answer", null);
//        }
//    }
//
//    // Update operation
//    @Operation(summary = "Update Answer", description = "Update Answer REST API is used to update an existing answer in the database")
//    @PutMapping("/{id}")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    public ResponseEntity<ApiResponse<AnswerDto>> updateAnswer(@PathVariable("id") Long id, @Valid @RequestBody AnswerDto answerDto) {
//        try {
//            AnswerDto updatedAnswer = answerService.updateAnswer(id, answerDto);
//            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Answer updated successfully", updatedAnswer);
//        } catch (Exception e) {
//            logger.error("Failed to update answer", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
//        }
//    }
//
//    // Delete operation
//    @Operation(summary = "Delete Answer", description = "Delete Answer REST API is used to delete a particular answer from the database")
//    @DeleteMapping("/{id}")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    public ResponseEntity<ApiResponse<String>> deleteAnswer(@PathVariable("id") Long id) {
//        try {
//            answerService.deleteAnswer(id);
//            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Answer successfully deleted", null);
//        } catch (Exception e) {
//            logger.error("Failed to delete answer", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete answer", null);
//        }
//    }
//}
