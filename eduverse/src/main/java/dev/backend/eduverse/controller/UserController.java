//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.UserDto;
//import dev.backend.eduverse.service.impl.UserServiceImpl;
//import dev.backend.eduverse.util.response_template.PageNumberResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    private final Logger logger = LoggerFactory.getLogger(UserController.class);
//    private final UserServiceImpl userService;
//    private final int PageSize = 10;
//
//    @Autowired
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping
//    @Operation(
//            summary = "Create a new user",
//            tags = {"User Operation"})
//    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDTO) {
//        try {
//            userService.createUser(userDTO);
//            return ResponseEntity.ok().body("User Created Successfully");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body("Failed to create User");
//        }
//    }
//
//    @GetMapping("/page/{pageNumber}")
//    @Operation(
//            summary = "Retrieve all users with Page",
//            tags = {"User Operation"})
//    public ResponseEntity<?> readUsers(@PathVariable int pageNumber) {
//        try {
//            List<UserDto> users = userService.readUserByPagniation(pageNumber, PageSize);
//            PageNumberResponse<List<UserDto>> response = new PageNumberResponse<>(pageNumber, PageSize, users);
//            return ResponseEntity.ok().body(response);
//        } catch (IllegalAccessException e) {
//            return ResponseEntity.badRequest().body("Failed to read the user");
//        }
//    }
//
//    @GetMapping("/{userId}")
//    @Operation(
//            summary = "Retrieve a user by ID",
//            tags = {"User Operation"}
//    )
//    public ResponseEntity<?> readUserById(@PathVariable Long userId) {
//        try {
//            UserDto user = userService.readUserById(userId);
//            return ResponseEntity.ok().body(user);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/email")
//    @Operation(
//            summary = "Retrieve users by email",
//            tags = {"User Operation"}
//    )
//    public ResponseEntity<?> readUserByEmail(@RequestParam String email) {
//        try {
//            List<UserDto> users = userService.searchByUserEmail(email);
//            return ResponseEntity.ok().body(users);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to retrieve the user by email");
//        }
//    }
//
//    @PutMapping("/{userId}")
//    @Operation(
//            summary = "Update a user's information",
//            tags = {"User Operation"}
//    )
//    public ResponseEntity<String> updateUser(
//            @PathVariable Long userId,
//            @Valid @RequestBody UserDto userDTO
//    ) {
//        try {
//            userService.updateUser(userId, userDTO);
//            return ResponseEntity.ok().body("User with" + userId + "is updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to updated the data");
//        }
//    }
//
//    @DeleteMapping("/{userId}")
//    @Operation(
//            summary = "Delete a user by ID",
//            tags = {"User Operation"}
//    )
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        try {
//            userService.deleteUser(userId);
//            return ResponseEntity.ok().body("User with this" + userId + " id is deleted successfully");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}
//
//  /*
//  @PostMapping("/bulk")
//  @Operation(
//      summary = "Create multiple users in bulk mode",
//      tags = {"User Operation"})
//  public ResponseEntity<String> createUserBulkMode(
//      @Valid @RequestBody List<UserDTO> userDTO) {
//    try {
//      userService.bulkCreateUser(userDTO);
//      return ResponseUtil.createSuccessResponse(
//          HttpStatus.OK, "Bulk User Creation Success", "create");
//    } catch (DataIntegrityViolationException e) {
//      return ResponseUtil.createErrorResponse(
//          HttpStatus.BAD_REQUEST, "Failed to create user in bulk way", e.getMessage());
//    } catch (Exception e) {
//      return ResponseUtil.createErrorResponse(
//          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
//    }
//  }*/
//
