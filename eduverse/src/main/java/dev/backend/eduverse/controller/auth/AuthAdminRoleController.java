package dev.backend.eduverse.controller.auth;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.backend.eduverse.dto.AdminRoleDto;
import dev.backend.eduverse.service.AdminRoleService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.PageNumberResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "CRUD REST APIs for Admin Role")
@RestController
@RequestMapping("/api/auth/admin/adminRole")
@RequiredArgsConstructor
public class AuthAdminRoleController {
	private final AdminRoleService adminRoleService;
	private final Logger logger = LoggerFactory.getLogger(AuthAdminRoleController.class);

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@PostMapping("")
	@Operation(summary = "Create a new ADMIN role")
	public ResponseEntity<ApiResponse<String>> createAdminRole(@Valid @RequestBody AdminRoleDto adminRoleDTO) {
		try {
			boolean created = adminRoleService.createAdminRole(adminRoleDTO);
			if (created) {
				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "ADMIN role created successfully", "created");
			} else {
				return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create ADMIN role",
						"Creation failed due to unknown reasons");
			}
		} catch (DataIntegrityViolationException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create ADMIN role",
					e.getMessage());
		} catch (Exception e) {
			logger.error("Failed to create adminRole", e);
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create ADMIN role",
					e.getMessage());
		}
	}

	@GetMapping("")
	@Operation(summary = "Retrieve all adminRoles")
	public ResponseEntity<ApiResponse<PageNumberResponse<List<AdminRoleDto>>>> readAdminRoles(
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		try {
			List<AdminRoleDto> adminRoleList = adminRoleService.readAdminRoleByPagniation(pageNo, limit);
			if (adminRoleList.isEmpty()) {
				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "No admin roles found",
						new PageNumberResponse<>(pageNo, limit, adminRoleList));
			} else {
				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Pathways retrieved successfully",
						new PageNumberResponse<>(pageNo, limit, adminRoleList));
			}
		} catch (Exception e) {
			logger.error("Failed to retrieve adminRoles", e);
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve admin roles",
					null);
		}
	}

	@PutMapping("/{adminRoleId}")
	@Operation(summary = "Update a adminRole's information")
	public ResponseEntity<ApiResponse<String>> updateAdminRole(@PathVariable Long adminRoleId,
			@Valid @RequestBody AdminRoleDto adminRoleDTO) {
		try {
			boolean updated = adminRoleService.updateAdminRole(adminRoleDTO, adminRoleId);
			if (updated) {
				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Admin Role updated successfully", "updated");
			} else {
				return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND,
						"Admin Role not found with ID: " + adminRoleId, null);
			}
		} catch (EntityNotFoundException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND,
					"Admin Role not found with ID: " + adminRoleId, e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update admin role",
					e.getMessage());
		}
	}

	@DeleteMapping("/{adminRoleId}")
	@Operation(summary = "Delete a ADMIN role by ID")
	public ResponseEntity<ApiResponse<String>> deleteAdminRole(@PathVariable Long adminRoleId) {
		try {
			boolean deleted = adminRoleService.deleteAdminRole(adminRoleId);
			if (deleted) {
				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "ADMIN role deleted successfully", "deleted");
			} else {
				return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND,
						"ADMIN role not found with ID: " + adminRoleId, null);
			}
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete ADMIN role",
					e.getMessage());
		}
	}
}
