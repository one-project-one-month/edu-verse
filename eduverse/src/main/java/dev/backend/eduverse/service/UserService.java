package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.UserDto;

import java.util.List;

public interface UserService {
    void createUser(UserDto userDTO);

    void bulkCreateUser(List<UserDto> userDTO);

    /*  List<UserDTO> readUser();*/

    UserDto readUserById(Long userId);

    List<UserDto> searchByUserEmail(String keyWord);

    void updateUser(Long userId, UserDto userDTO);

    void deleteUser(Long userId);

    Long searchIDByUserEmail(String email);

    List<UserDto> readUserByPagniation(int page, int offset) throws IllegalAccessException;

	List<Object[]> getAllRegisteredCourses(Long id);

}
