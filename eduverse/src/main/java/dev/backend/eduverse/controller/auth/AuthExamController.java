/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/10/2024
 * @Time : 6:04 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.ExamDto;
import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.service.ExamService;
import dev.backend.eduverse.service.QuestionService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Exam")
@RestController
@RequestMapping("/api/auth/admin/exam")
@RequiredArgsConstructor
public class AuthExamController {

    private final Logger logger = LoggerFactory.getLogger(AuthQuestionController.class);
    private final ExamService examService;

    @Operation(summary = "Create Exam", description = "Create Exam REST API is used to save Exam in a database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    @PostMapping("")
    public ResponseEntity<ApiResponse<ExamDto>> createExam(@Valid @RequestBody ExamDto exam) {
        try {
            ExamDto createdExam = examService.createExam(exam);
            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Exam created successfully",
                    createdExam);
        } catch (Exception e) {
            logger.error("Failed to create exam", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create exam",
                    null);
        }
    }

    @Operation(summary = "Update Exam", description = "Update Exam REST API is used to update a particular Exam in the database")
    @PutMapping("/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<ApiResponse<ExamDto>> updateExam(@PathVariable("id") Long id,
                                                                   @Valid @RequestBody ExamDto exam) {
        try {
            ExamDto updatedExam = examService.updateExam(id, exam);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Exam updated successfully", updatedExam);
        } catch (Exception e) {
            logger.error("Failed to update exam", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update exam",
                    null);
        }
    }

    @Operation(summary = "Delete Exam", description = "Delete Exam REST API is used to delete a particular Exam from the database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable("id") Long id) {

        examService.deleteExam(id);
        return new ResponseEntity<>("Exam successfully deleted!", HttpStatus.OK);
    }
}
