///**
// * @Author : Kyaw Zaw Htet
// * @Date : 6/4/2024
// * @Time : 8:32 AM
// * @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.QuestionDto;
//import dev.backend.eduverse.dto.UserExamDto;
//import dev.backend.eduverse.service.UserExamService;
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
//@Tag(name = "CRUD REST APIs for UserExam")
//@RestController
//@AllArgsConstructor
//@RequestMapping("/api/userexam")
//public class UserExamController {
//
//    private final Logger logger = LoggerFactory.getLogger(UserExamController.class);
//
//    private UserExamService userExamService;
//
//    @Operation(
//            summary = "Create UserExam",
//            description = "Create UserExam REST API is used to save UserExam in a database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
//    @PostMapping("/")
//    public ResponseEntity<ApiResponse<UserExamDto>> createUserExam(
//            @Valid @RequestBody UserExamDto userExam) {
//        try {
//            UserExamDto createdUserExam = userExamService.createUserExam(userExam);
//            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "UserExam created successfully", createdUserExam);
//        } catch (Exception e) {
//            logger.error("Failed to create userexam", e);
//            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create userexam", null);
//        }
//    }
//
//    @Operation(
//            summary = "Get All UserExam",
//            description =
//                    "Get All UserExam REST API is used to get all the UserExams from the database"
//    )
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @GetMapping("")
//    public ResponseEntity<ApiResponse<PageNumberResponse<List<UserExamDto>>>> getAllUserExams(
//            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
//            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
//    ) {
//        return ResponseUtil.getApiResponseResponseEntity(pageNo, limit,
//                paginationParams -> {
//                    try {
//                        return userExamService.readUserExamByPagination(paginationParams.pageNo(), paginationParams.limit());
//                    } catch (IllegalAccessException e) {
//                        throw new RuntimeException(e);
//                    }
//                },
//                logger,
//                "No userexams found",
//                "UserExams retrieved successfully");
//    }
//
//    @Operation(
//            summary = "Update UserExam",
//            description =
//                    "Update UserExam REST API is used to update a particular UserExam in the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @PutMapping("{id}")
//    public ResponseEntity<UserExamDto> updateUserExam(
//            @PathVariable("id") Long id, @RequestBody @Valid UserExamDto userExam) {
//
//        userExam.setId(id);
//        UserExamDto updatedUserExam = userExamService.updateUserExam(userExam);
//        return new ResponseEntity<>(updatedUserExam, HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Delete UserExam",
//            description =
//                    "Delete UserExam REST API is used to delete a particular UserExam from the database")
//    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteUserExam(@PathVariable("id") Long id) {
//
//        userExamService.deleteUserExam(id);
//        return new ResponseEntity<>("UserExam successfully deleted!", HttpStatus.OK);
//    }
//}
