package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import dev.backend.eduverse.util.ResponeTemplate.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * UserController class responsible for handling user-related operations.
 * This class defines endpoints for creating, reading, updating, and deleting users.
 *
 * Business Logic:
 * - The UserController interacts with UserServiceImpl to perform CRUD operations on user data.
 * - For creating a new user, the endpoint '/api/users/create' accepts a UserDTO object and delegates the creation to the UserService.
 * - Bulk creation of users is supported through the endpoint '/api/users/create/bulk', accepting a list of UserDTO objects.
 * - The endpoint '/api/users/getId/{email}' retrieves the user ID by email to provide flexibility when retrieving user information.
 * - User retrieval operations are provided through '/api/users/read' to get all users, and '/api/users/id/{userId}' to retrieve a user by ID.
 * - Retrieval by email is available at '/api/users/{email}'.
 * - Updating a user's information is done via '/api/users/update/{userId}', accepting the user ID and updated UserDTO.
 * - User deletion is handled by the '/api/users/delete/{userId}' endpoint.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserServiceImpl userService;

  @Autowired
  public UserController(UserServiceImpl userService) {

    this.userService = userService;
  }
    /**
     * Creates a new user.
     *
     * @param userDTO The user data to create.
     * @return ResponseEntity containing ApiResponse with status and message.
     */

    @PostMapping("/create")
    @Operation(summary = "Create a new user", tags = {"User Operation"})
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

    /**
     * Creates multiple users in bulk mode.
     *
     * @param userDTO The list of user data to create.
     * @return ResponseEntity containing ApiResponse with status and message.
     */
    @PostMapping("/create/bulk")
    @Operation(summary = "Create multiple users in bulk mode", tags = {"User Operation"})
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

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing ApiResponse with status and list of users.
     */
    @GetMapping("/read")
    @Operation(summary = "Retrieve all users", tags = {"User Operation"})
    public ResponseEntity<ApiResponse<List<UserDTO>>> readUsers() {
      try {
        List<UserDTO> users = userService.readUser();
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Users retrieved successfully", users);
      } catch (Exception e) {
        return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
      }
    }


    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user.
     * @return ResponseEntity containing ApiResponse with status and user data.
     */
    @GetMapping("/id/{userId}")
    @Operation(summary = "Retrieve a user by ID", tags = {"User Operation"})
    public ResponseEntity<ApiResponse<UserDTO>> readUserById(@PathVariable(name = "userId") Long userId) {
      try {
        UserDTO user = userService.readUserById(userId);
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User retrieved successfully", user);
      } catch (Exception e) {
        return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user", null);
      }
    }



    /**
     * Retrieves users by email.
     *
     * @param email The email to search for.
     * @return ResponseEntity containing ApiResponse with status and list of users.
     */
    @GetMapping("/{email}")
    @Operation(summary = "Retrieve users by email", tags = {"User Operation"})
    public ResponseEntity<ApiResponse<List<UserDTO>>> readUserByEmail(@PathVariable String email) {
      try {
        List<UserDTO> users = userService.searchByUserEmail(email);
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Users retrieved successfully", users);
      } catch (Exception e) {
        return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
      }
    }

    /**
     * Updates a user's information.
     *
     * @param userId  The ID of the user to update.
     * @param userDTO The updated user data.
     * @return ResponseEntity containing ApiResponse with status and message.
     */
    @PostMapping("/update/{userId}")
    @Operation(summary = "Update a user's information", tags = {"User Operation"})
    public ResponseEntity<ApiResponse<String>> updateUser(
            @PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
      try {
        userService.updateUser(userId, userDTO);
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User updated successfully", "updated");
      } catch (Exception e) {
        return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user", e.getMessage());
      }
    }

  @GetMapping("/getId/{email}")
  @Operation(summary = "Retrieve usersId by email", tags = {"User Operation"})
  public ResponseEntity<ApiResponse<Long>> readUserIdByEmail(@PathVariable String email) {
    try {
      Long userId = userService.searchIDByUserEmail(email);
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Users retrieved successfully", userId);
    } catch (Exception e) {
      return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }
    /**
     * Deletes a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @return ResponseEntity containing ApiResponse with status and message.
     */
    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete a user by ID", tags = {"User Operation"})
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
