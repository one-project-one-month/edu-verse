package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.UserServices;
import dev.backend.eduverse.model.User;
import dev.backend.eduverse.util.ResponeTemplate.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


/**
 * Service layer Updated according to the Ko Zay Mentoring
 */
@Service
public class UserServiceImpl implements UserServices {

  /**
   * @Field Logger, Dependency Injection with field Injection
   */
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserRepository userRepository;

  private final EntityMapper entityMapper;
  @Autowired
  private ModelMapper modelMapper;

  /**
   * Constructor Injection
   */
  @Autowired
  public UserServiceImpl(UserRepository userRepository, EntityMapper entityMapper) {
    this.userRepository = userRepository;
    this.entityMapper = entityMapper;
  }

  /**
   * @Method  Creation User
   */
  @Override
  public void createUser(UserDTO userDTO) {
    logger.info("Entering the creation process");
    User user = modelMapper.map(userDTO, User.class);
    userRepository.save(user);
    logger.info("User created successfully");
  }

  /**
   *Bulk Creator
   */
  @Override
  public void bulkCreateUser(List<UserDTO> userDTO) {
    logger.info("Entering the Bulk creation process");
    List<User> users = userDTO.stream()
            .map(dto -> entityMapper.mapDTOtoEntity(dto, new User()))
            .collect(Collectors.toList());
    userRepository.saveAll(users);
    logger.info("Successful Bulk user Creation");
  }

  /**
   *
   * @return BulkReader
   */
  @Override
  public List<UserDTO> readUser() {
    logger.info("Entering the read process");
    List<User> users = userRepository.findAll();
    logger.info("Retrieved users: " + users);
    List<UserDTO> userDTOs = users.stream()
            .map(user -> entityMapper.mapDTOtoEntity(user, new UserDTO()))
            .peek(userDTO -> {
                    userDTO.setPassword("Private Credential");
                    logger.info("Mapped UserDTO: " + userDTO);})
            .collect(Collectors.toList());

    return userDTOs;
  }
  /**
   *Single Reader
   */
  @Override
  public UserDTO readUserById(Long userId) {
    logger.info("Retrieving user by ID: {}", userId);
    Optional<User> userOptional =  userRepository.findById(userId);
    User user = userOptional.orElseThrow(() -> new EntityNotFoundException("Entity is not found with this id" + userId));
    return entityMapper.mapDTOtoEntity(user,new UserDTO());
  }

  /**
   Search By email*/
  @Override
  public List<UserDTO> searchByUserEmail(String keyWord) {
      Optional<List<User>> usersOptional = userRepository.findUserByEmailContainingIgnoreCase(keyWord);
      List<User> users = usersOptional.orElseThrow(()-> new EntityNotFoundException("Entity not found"));
      return users.stream()
              .map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
  }

  /** Update User
   */

  @Override
  public void updateUser(Long userId, UserDTO userDTO) {
    logger.info("Updating user with ID: {}", userId);
    Optional<User> existingUserOptional = userRepository.findById(userId);
    User existingUser = existingUserOptional.orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    modelMapper.map(existingUser, userDTO);
    userRepository.save(existingUser);
    logger.info("User updated successfully");
  }

  /**
   *
   * DeletebyID
   */
  @Override
  public void deleteUser(Long userId) {
    logger.info("Deleting user with ID: {}", userId);
    try {
      userRepository.deleteById(userId);
      logger.info("User deleted successfully");
    } catch (EmptyResultDataAccessException e) {
      logger.error("User not found for deletion with ID: {}", userId);
      throw new EntityNotFoundException("User not found with ID: " + userId);
    }
  }

}
