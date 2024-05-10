package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface UserServices {
  void createUser(UserDTO userDTO);
  void bulkCreateUser(List<UserDTO> userDTO);
  List<UserDTO> readUser();
  UserDTO readUserById(Long userId);
  List<UserDTO> searchByUserEmail(String keyWord);
  void updateUser(Long userId, UserDTO userDTO);
  void deleteUser(Long userId);

  Long searchIDByUserEmail(String email);

}
