///*
// * @Author : Alvin
// * @Date : 5/11/2024
// * @Time : 9:00 PM
// * @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.AdminRoleDto;
//import dev.backend.eduverse.service.AdminRoleService;
//import dev.backend.eduverse.util.response_template.ApiResponse;
//import dev.backend.eduverse.util.response_template.PageNumberResponse;
//import dev.backend.eduverse.util.response_template.ResponseUtil;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.validation.Valid;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.StringTrimmerEditor;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "CRUD REST APIs for ADMIN role", description = "CRUD REST APIs - Create ADMIN role, Update ADMIN role, Get All ADMIN role List, Delete"
//		+ " ADMIN role")
//@RestController
//@RequestMapping("/api/adminRole")
//public class AdminRoleController {
//	private final Logger logger = LoggerFactory.getLogger(AdminRoleController.class);
//
//	@InitBinder
//	public void initBinder(WebDataBinder dataBinder) {
//		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//	}
//
//	@Autowired
//	private final AdminRoleService adminRoleService;
//
//	public AdminRoleController(AdminRoleService adminRoleService) {
//		this.adminRoleService = adminRoleService;
//	}
//
//	@PostMapping("/")
//	@Operation(summary = "Create a new ADMIN role", tags = { "ADMIN role Creator" })
//	public ResponseEntity<ApiResponse<String>> createAdminRole(@Valid @RequestBody AdminRoleDto adminRoleDTO) {
//		try {
//			boolean created = adminRoleService.createAdminRole(adminRoleDTO);
//			if (created) {
//				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "ADMIN role created successfully", "created");
//			} else {
//				return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create ADMIN role",
//						"Creation failed due to unknown reasons");
//			}
//		} catch (DataIntegrityViolationException e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create ADMIN role",
//					e.getMessage());
//		} catch (Exception e) {
//			logger.error("Failed to create adminRole", e);
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create ADMIN role",
//					e.getMessage());
//		}
//	}
//
//	@GetMapping("")
//	@Operation(summary = "Retrieve all adminRoles", tags = { "ADMIN role Reader" })
//	public ResponseEntity<ApiResponse<PageNumberResponse<List<AdminRoleDto>>>> readAdminRoles(
//			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
//			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
//		try {
//			List<AdminRoleDto> adminRoleList = adminRoleService.readAdminRoleByPagniation(pageNo, limit);
//			if (adminRoleList.isEmpty()) {
//				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "No admin roles found",
//						new PageNumberResponse<>(pageNo, limit, adminRoleList));
//			} else {
//				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Pathways retrieved successfully",
//						new PageNumberResponse<>(pageNo, limit, adminRoleList));
//			}
//		} catch (Exception e) {
//			logger.error("Failed to retrieve adminRoles", e);
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve admin roles",
//					null);
//		}
//	}
//
//	@PutMapping("/{adminRoleId}")
//	@Operation(summary = "Update a adminRole's information", tags = { "Update ADMIN role" })
//	public ResponseEntity<ApiResponse<String>> updateAdminRole(@PathVariable Long adminRoleId,
//			@Valid @RequestBody AdminRoleDto adminRoleDTO) {
//		return getApiResponseResponseEntity(adminRoleId, adminRoleDTO, adminRoleService);
//	}
//
//	@DeleteMapping("/{adminRoleId}")
//	@Operation(summary = "Delete a ADMIN role by ID", tags = { "Delete ADMIN role By Id" })
//	public ResponseEntity<ApiResponse<String>> deleteAdminRole(@PathVariable Long adminRoleId) {
//		try {
//			boolean deleted = adminRoleService.deleteAdminRole(adminRoleId);
//			if (deleted) {
//				return ResponseUtil.createSuccessResponse(HttpStatus.OK, "ADMIN role deleted successfully", "deleted");
//			} else {
//				return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND,
//						"ADMIN role not found with ID: " + adminRoleId, null);
//			}
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete ADMIN role",
//					e.getMessage());
//		}
//	}
//}
