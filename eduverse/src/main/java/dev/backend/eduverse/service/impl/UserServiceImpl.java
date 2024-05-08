package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.repository.UserRepository;
import dev.backend.eduverse.service.UserServices;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import dev.backend.eduverse.util.ResponeTemplate.EntityMapper;
import dev.backend.eduverse.util.ResponeTemplate.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserRepository userRepository;
  private final EntityMapper entityMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, EntityMapper entityMapper) {
    this.userRepository = userRepository;
    this.entityMapper = entityMapper;
  }

  @Override
  public ResponseEntity<ApiResponse<String>> createUser(UserDTO userDTO) {
    try {
      logger.info("Entering the creation process");
      User user = entityMapper.mapDTOtoEntity(userDTO, new User());
      userRepository.save(user);
      logger.info("User created successfully");
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User created successfully", "created");
    } catch (DataIntegrityViolationException e) {
      logger.error("Error creating user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.BAD_REQUEST, "Failed to create user", e.getMessage());
    } catch (Exception e) {
      logger.error("Error creating user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @Override
  public ResponseEntity<ApiResponse<String>> bulkCreateUser(List<UserDTO> userDTO) {
    try {
      logger.info("Entering the Bulk creation process");
      List<User> users =
          userDTO.stream()
              .map(dto -> entityMapper.mapDTOtoEntity(dto, new User()))
              .collect(Collectors.toList());
      userRepository.saveAll(users);
      logger.info("Successful Bulk user Creation");
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, " Bulk User Creation Success", "create");
    } catch (DataIntegrityViolationException e) {
      logger.error("Error creating user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.BAD_REQUEST, "Failed to create user in bulk way", e.getMessage());
    } catch (Exception e) {
      logger.error("Error creating user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", e.getMessage());
    }
  }

  @Override
  public ResponseEntity<ApiResponse<List<User>>> readUser() {
    try {
      logger.info("Entering the read process");
      List<User> users = userRepository.findAll();
      logger.info("Exiting the read process");
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "Users retrieved successfully", users);
    } catch (Exception e) {
      logger.error("Error retrieving users: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve users", null);
    }
  }

  @Override
  public ResponseEntity<ApiResponse<User>> readUserById(Long userId) {
    try {
      logger.info("Retrieving user by ID: {}", userId);
      User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
      logger.info("User retrieved successfully");
      return ResponseUtil.createSuccessResponse(HttpStatus.OK, "User retrieved successfully", user);
    } catch (Exception e) {
      logger.error("Error retrieving user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve user", null);
    }
  }

  @Override
  public ResponseEntity<ApiResponse<List<User>>> searchByUserEmail(String keyWord) {
    Optional<List<User>> user = userRepository.findUserByEmailContainingIgnoreCase(keyWord);
    return user.map(
            userList ->
                ResponseUtil.createErrorResponse(
                    HttpStatus.OK, "Find By Email Successes", userList))
        .orElse(null);
  }

  @Override
  public ResponseEntity<ApiResponse<String>> updateUser(Long userId, UserDTO userDTO) {
    try {
      logger.info("Updating user with ID: {}", userId);
      User existingUser =
          userRepository
              .findById(userId)
              .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

      entityMapper.mapDTOtoEntity(existingUser, userDTO);
      userRepository.save(existingUser);

      logger.info("User updated successfully");
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User updated successfully", "updated");
    } catch (Exception e) {
      logger.error("Error updating user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user", e.getMessage());
    }
  }

  @Override
  public ResponseEntity<ApiResponse<String>> deleteUser(Long userId) {
    try {
      logger.info("Deleting user with ID: {}", userId);
      userRepository.deleteById(userId);
      logger.info("User deleted successfully");
      return ResponseUtil.createSuccessResponse(
          HttpStatus.OK, "User deleted successfully", "deleted");
    } catch (EmptyResultDataAccessException e) {
      logger.error("User not found for deletion with ID: {}", userId);
      return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "User not found", null);
    } catch (Exception e) {
      logger.error("Error deleting user: {}", e.getMessage());
      return ResponseUtil.createErrorResponse(
          HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete user", null);
    }
  }
}
