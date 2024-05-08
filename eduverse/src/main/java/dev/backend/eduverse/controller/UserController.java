package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    return userService.createUser(userDTO);
  }

  @Tag(name = "User Bulk Creator", description = "Post From UserEndpoint")
  @PostMapping("/create/bulk")
  public ResponseEntity<ApiResponse<String>> createUserBulkMode(
      @Valid @RequestBody List<UserDTO> userDTO) {
    return userService.bulkCreateUser(userDTO);
  }

  @Tag(name = "User Reader", description = "Get From UserEndpoint")
  @GetMapping("/read")
  public ResponseEntity<ApiResponse<List<User>>> readUsers() {
    return userService.readUser();
  }

  @Tag(name = "User Reader By ID", description = "Get From UserEndpoint")
  @GetMapping("/read/id/{user}")
  public ResponseEntity<ApiResponse<User>> readUserById(@PathVariable(name = "user") Long userId) {
    return userService.readUserById(userId);
  }

  @Tag(name = "User Reader By Email", description = "Get From UserEndpoint")
  @GetMapping("/read/email/{email}")
  public ResponseEntity<ApiResponse<List<User>>> readUserByEmail(@PathVariable String email) {
    return userService.searchByUserEmail(email);
  }

  @Tag(name = "Update User Body", description = "Post From UserEndpoint")
  @PostMapping("/update/{userId}")
  public ResponseEntity<ApiResponse<String>> updateUser(
      @PathVariable Long userId, @Valid @RequestBody UserDTO userDTO) {
    logger.info("Update Controller called ");
    return userService.updateUser(userId, userDTO);
  }

  @Tag(name = "Delete User By Id", description = "Delete From UserEndpoint")
  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long userId) {
    return userService.deleteUser(userId);
  }
}
