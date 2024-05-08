package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserServices {
  ResponseEntity<ApiResponse<String>> createUser(UserDTO userDTO);
  ResponseEntity<ApiResponse<String>> bulkCreateUser(List<UserDTO> userDTO);
  ResponseEntity<ApiResponse<List<User>>> readUser();
  ResponseEntity<ApiResponse<User>> readUserById(Long userId);
  ResponseEntity<ApiResponse<List<User>>> searchByUserEmail(String keyWord);
  ResponseEntity<ApiResponse<String>> updateUser(Long userId, UserDTO userDTO);
  ResponseEntity<ApiResponse<String>> deleteUser(Long userId);
}
