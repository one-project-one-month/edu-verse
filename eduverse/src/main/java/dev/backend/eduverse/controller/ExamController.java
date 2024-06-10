///**
// * @Author : Kyaw Zaw Htet
// * @Date : 6/4/2024
// * @Time : 8:07 AM
// * @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.ExamDto;
//import dev.backend.eduverse.model.Exam;
//import dev.backend.eduverse.service.ExamService;
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
//@Tag(name = "CRUD REST APIs for Exam")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/exam")
//public class ExamController {
//    private final Logger logger = LoggerFactory.getLogger(ExamController.class);
//
//    private ExamService examService;
//
//    @Operation(summary = "Create Exam", description = "Create Exam REST API is used to save Exam in a database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
//    @PostMapping("/")
//    public ResponseEntity<ApiResponse<ExamDto>> createExam(@Valid @RequestBody ExamDto exam) {
//        try {
//            ExamDto createdExam = examService.createExam(exam);
//            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Exam created successfully", createdExam);
//        } catch (Exception e) {
//            logger.error("Failed to create exam", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create exam", null);
//        }
//    }
//
//    @Operation(summary = "Get All Exam", description = "Get All Exam REST API is used to get all the Exams from the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @GetMapping("")
//    public ResponseEntity<ApiResponse<PageNumberResponse<List<ExamDto>>>> getAllExams(@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo, @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit, paginationParams -> {
//            try {
//                return examService.readExamByPagination(paginationParams.pageNo(), paginationParams.limit());
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }, logger, "No exams found", "Exams retrieved successfully");
//    }
//
//    @Operation(
//            summary = "Get Exam By Id",
//            description =
//                    "Get Exam By Id REST API is used to get a single Exam from the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @GetMapping("{id}")
//    public ResponseEntity<ExamDto> getExamById(@PathVariable("id") Long id) {
//
//        ExamDto exam = examService.getExamById(id);
//        return new ResponseEntity<>(exam, HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Update Exam",
//            description =
//                    "Update Exam REST API is used to update a particular Exam in the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @PutMapping("{id}")
//    public ResponseEntity<ExamDto> updateExam(
//            @PathVariable("id") Long id, @RequestBody @Valid ExamDto exam) {
//
//        exam.setId(id);
//        ExamDto updatedExam = examService.updateExam(id, exam);
//        return new ResponseEntity<>(updatedExam, HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Delete Exam",
//            description =
//                    "Delete Exam REST API is used to delete a particular Exam from the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteExam(@PathVariable("id") Long id) {
//        examService.deleteExam(id);
//        return new ResponseEntity<>("Exam successfully deleted!", HttpStatus.OK);
//    }
//}
