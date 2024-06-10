package dev.backend.eduverse.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.backend.eduverse.dto.PathwayDto;
import dev.backend.eduverse.exception.NameAlreadyExistException;
import dev.backend.eduverse.service.PathwayService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Tag(name = "CRUD REST APIs for Pathway")
@RestController
@RequestMapping("/api/auth/admin/pathway")
public class AuthPathwayController {

    private final Logger logger = LoggerFactory.getLogger(AuthPathwayController.class);

    private final PathwayService pathwayService;

    public AuthPathwayController(PathwayService pathwayService) {
        this.pathwayService = pathwayService;
    }

    @PostMapping("")
    @Operation(summary = "Create a new pathway")
    public ResponseEntity<ApiResponse<String>> createPathway(@Valid @RequestBody PathwayDto pathwayDTO) {
        try {
            boolean created = pathwayService.createPathway(pathwayDTO);
            if (created) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Pathway created successfully", "created");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create pathway",
                        "Creation failed due to unknown reasons");
            }
        } catch (NameAlreadyExistException e) {
            String errorMessage = "Pathway with name '" + pathwayDTO.getName() + "' already exists";
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create pathway", errorMessage);
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create pathway", e.getMessage());
        } catch (Exception e) {
            logger.error("Failed to create pathway", e);
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create pathway",
                    e.getMessage());
        }
    }

    @PutMapping("/{pathwayId}")
    @Operation(summary = "Update a pathway's information")
    public ResponseEntity<ApiResponse<String>> updatePathway(@PathVariable Long pathwayId,
                                                             @Valid @RequestBody PathwayDto pathwayDTO) {
        try {
            boolean updated = pathwayService.updatePathway(pathwayDTO, pathwayId);
            if (updated) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Pathway updated successfully", "updated");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Pathway not found with ID: " + pathwayId,
                        null);
            }
        } catch (EntityNotFoundException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Pathway not found with ID: " + pathwayId,
                    e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update pathway",
                    e.getMessage());
        }
    }

    @DeleteMapping("/{pathwayId}")
    @Operation(summary = "Delete a pathway by ID")
    public ResponseEntity<ApiResponse<String>> deletePathway(@PathVariable Long pathwayId) {
        try {
            boolean deleted = pathwayService.deletePathway(pathwayId);
            if (deleted) {
                return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Pathway deleted successfully", "deleted");
            } else {
                return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Pathway not found with ID: " + pathwayId,
                        null);
            }
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete pathway",
                    e.getMessage());
        }
    }
}
