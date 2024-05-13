package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import dev.backend.eduverse.util.ResponeTemplate.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {

    this.userService = userService;
  }

  @PostMapping("/")
  @Operation(
      summary = "Create a new user",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody UserDTO userDTO) {
    try {
      userService.createUser(userDTO);
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User created successfully", "created");
    } catch (DataIntegrityViolationException e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.BAD_REQUEST, "Failed to create user", e.getMessage());
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @PostMapping("/bulk")
  @Operation(
      summary = "Create multiple users in bulk mode",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<String>> createUserBulkMode(
      @Valid @RequestBody List<UserDTO> userDTO) {
    try {
      userService.bulkCreateUser(userDTO);
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "Bulk User Creation Success", "create");
    } catch (DataIntegrityViolationException e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.BAD_REQUEST, "Failed to create user in bulk way", e.getMessage());
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @GetMapping("/")
  @Operation(
      summary = "Retrieve all users",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<List<UserDTO>>> readUsers() {
    try {
      List<UserDTO> users = userService.readUser();
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "Users retrieved successfully", users);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }

  @GetMapping("/{userId}")
  @Operation(
      summary = "Retrieve a user by ID",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<UserDTO>> readUserById(
      @PathVariable(name = "userId") Long userId) {
    try {
      UserDTO user = userService.readUserById(userId);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User retrieved successfully", user);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user", null);
    }
  }

  @GetMapping("/{email}")
  @Operation(
      summary = "Retrieve users by email",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<List<UserDTO>>> readUserByEmail(@PathVariable String email) {
    try {
      List<UserDTO> users = userService.searchByUserEmail(email);
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "Users retrieved successfully", users);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }

  @PostMapping("/{userId}")
  @Operation(
      summary = "Update a user's information",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<String>> updateUser(
      @PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
    try {
      userService.updateUser(userId, userDTO);
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User updated successfully", "updated");
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user", e.getMessage());
    }
  }

  @DeleteMapping("/{userId}")
  @Operation(
      summary = "Delete a user by ID",
      tags = {"User Operation"})
  public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long userId) {
    try {
      userService.deleteUser(userId);
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User deleted successfully", "deleted");
    } catch (EmptyResultDataAccessException e) {
      return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "User not found", null);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user", e.getMessage());
    }
  }
}
