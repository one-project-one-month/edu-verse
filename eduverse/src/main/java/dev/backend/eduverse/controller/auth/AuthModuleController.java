/**
 * @Author : Kyaw Zaw Htet
 * @Date : 6/10/2024
 * @Time : 6:12 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.ModuleDto;
import dev.backend.eduverse.dto.QuestionDto;
import dev.backend.eduverse.service.ModuleService;
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

@Tag(name = "CRUD REST APIs for Module")
@RestController
@RequestMapping("/api/auth/admin/module")
@RequiredArgsConstructor
public class AuthModuleController {

    private final Logger logger = LoggerFactory.getLogger(AuthQuestionController.class);
    private ModuleService moduleService;

    @Operation(summary = "Create Module", description = "Create Module REST API is used to save Module in a database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    @PostMapping("")
    public ResponseEntity<ApiResponse<ModuleDto>> createQuestion(@Valid @RequestBody ModuleDto module) {
        try {
            ModuleDto createdModule = moduleService.createModule(module);
            return ResponseUtil.createSuccessResponse(HttpStatus.CREATED, "Module created successfully",
                    createdModule);
        } catch (Exception e) {
            logger.error("Failed to create module", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create module",
                    null);
        }
    }

    @Operation(summary = "Update Module", description = "Update Module REST API is used to update a particular Module in the database")
    @PutMapping("/{id}")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    public ResponseEntity<ApiResponse<ModuleDto>> updateModule(@PathVariable("id") Long id,
                                                                   @Valid @RequestBody ModuleDto module) {
        try {
            ModuleDto updatedModule = moduleService.updateModule(module, id);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module updated successfully", updatedModule);
        } catch (Exception e) {
            logger.error("Failed to update module", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update module",
                    null);
        }
    }

    @Operation(summary = "Delete Module", description = "Delete Module REST API is used to delete a particular Module from the database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModule(@PathVariable("id") Long id) {

        moduleService.deleteModule(id);
        return new ResponseEntity<>("Module successfully deleted!", HttpStatus.OK);
    }
}
