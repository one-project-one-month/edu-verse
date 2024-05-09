package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import dev.backend.eduverse.util.ResponeTemplate.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/users")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {

    this.userService = userService;
  }

  @Tag(name = "User Creator", description = "Post From UserEndpoint")
  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> createUser(@Valid @RequestBody UserDTO userDTO) {

    try {
      userService.createUser(userDTO);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User created successfully", "created");
    } catch (DataIntegrityViolationException e) {
      return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create user", e.getMessage());
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @Tag(name = "User Bulk Creator", description = "Post From UserEndpoint")
  @PostMapping("/create/bulk")
  public ResponseEntity<ApiResponse<String>> createUserBulkMode(@Valid @RequestBody List<UserDTO> userDTO) {

    try {
      userService.bulkCreateUser(userDTO);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Bulk User Creation Success", "create");
    } catch (DataIntegrityViolationException e) {
      return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create user in bulk way", e.getMessage());
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @Tag(name = "User Reader", description = "Get From UserEndpoint")
  @GetMapping("/read")
  public ResponseEntity<ApiResponse<List<UserDTO>>> readUsers() {
    try {
      List<UserDTO> users = userService.readUser();
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Users retrieved successfully", users);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }

  @Tag(name = "User Reader By ID", description = "Get From UserEndpoint")
  @GetMapping("/{user}")
  public ResponseEntity<ApiResponse<UserDTO>> readUserById(@PathVariable(name = "user") Long userId) {
    try {
      UserDTO user = userService.readUserById(userId);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User retrieved successfully", user);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user", null);
    }
  }

  @Tag(name = "User Reader By Email", description = "Get From UserEndpoint")
  @GetMapping("/email/{email}")
  public ResponseEntity<ApiResponse<List<UserDTO>>> readUserByEmail(@PathVariable String email) {

    try {
      List<UserDTO> users = userService.searchByUserEmail(email);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Users retrieved successfully", users);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }

  @Tag(name = "Update User Body", description = "Post From UserEndpoint")
  @PostMapping("/id/{userId}")
  public ResponseEntity<ApiResponse<String>> updateUser(
          @PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {

    try {
      userService.updateUser(userId, userDTO);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User updated successfully", "updated");
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user", e.getMessage());
    }
  }

  @Tag(name = "Delete User By Id", description = "Delete From UserEndpoint")
  @DeleteMapping("id/{userId}")
  public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long userId) {

    try {
      userService.deleteUser(userId);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User deleted successfully", "deleted");
    } catch (EmptyResultDataAccessException e) {
      return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "User not found", null);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user", e.getMessage());
    }
  }

}