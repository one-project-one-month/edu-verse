package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserDTO;
import java.util.List;

public interface UserServices {
  void createUser(UserDTO userDTO);

  void bulkCreateUser(List<UserDTO> userDTO);

/*  List<UserDTO> readUser();*/

  UserDTO readUserById(Long userId);

  List<UserDTO> searchByUserEmail(String keyWord);

  void updateUser(Long userId, UserDTO userDTO);

  void deleteUser(Long userId);

  Long searchIDByUserEmail(String email);

	List<UserDTO> readUserByPagniation(int page, int offset) throws IllegalAccessException;

}
