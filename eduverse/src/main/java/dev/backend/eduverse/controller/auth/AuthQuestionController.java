/*
 * @Author : Alvin
 * @Date : 6/10/2024
 * @Time : 04:10 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.service.QuestionService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "CRUD REST APIs for Question")
@RestController
@RequestMapping("/api/auth/admin/question")
@RequiredArgsConstructor
public class AuthQuestionController {
	private final Logger logger = LoggerFactory.getLogger(AuthQuestionController.class);
	private final QuestionService questionService;

	@Operation(summary = "Create Question", description = "Create Question REST API is used to save Question in a database")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
	@PostMapping("")
	public ResponseEntity<ApiResponse<QuestionDto>> createQuestion(@Valid @RequestBody QuestionDto question) {
		try {
			QuestionDto createdQuestion = questionService.createQuestion(question);
			return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Question created successfully",
					createdQuestion);
		} catch (Exception e) {
			logger.error("Failed to create question", e);
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create question",
					null);
		}
	}

	@Operation(summary = "Update Question", description = "Update Question REST API is used to update a particular Question in the database")
	@PutMapping("/{id}")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	public ResponseEntity<ApiResponse<QuestionDto>> updateQuestion(@PathVariable("id") Long id,
			@Valid @RequestBody QuestionDto question) {
		try {
			QuestionDto updatedQuestion = questionService.updateQuestion(id, question);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Question updated successfully", updatedQuestion);
		} catch (Exception e) {
			logger.error("Failed to update question", e);
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update question",
					null);
		}
	}

	@Operation(summary = "Delete Question", description = "Delete Question REST API is used to delete a particular Question from the database")
	@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {

		questionService.deleteQuestion(id);
		return new ResponseEntity<>("Question successfully deleted!", HttpStatus.OK);
	}
}
