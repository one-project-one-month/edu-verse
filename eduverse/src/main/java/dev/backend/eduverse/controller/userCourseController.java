package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.userCourseDTO;
import dev.backend.eduverse.service.userCourseServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userCourses")
public class userCourseController {

  @Autowired private userCourseServices userCourseService;

  @PostMapping
  public ResponseEntity<userCourseDTO> createUserCourse(@RequestBody userCourseDTO userCourseDTO) {
    userCourseDTO createdUserCourse = userCourseService.createUserCourse(userCourseDTO);
    return new ResponseEntity<>(createdUserCourse, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<userCourseDTO> getUserCourseById(@PathVariable long id) {
    userCourseDTO userCourseDTO = userCourseService.getUserCourseById(id);
    if (userCourseDTO != null) {
      return new ResponseEntity<>(userCourseDTO, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping
  public ResponseEntity<List<userCourseDTO>> getAllUserCourses() {
    List<userCourseDTO> userCourseDTOs = userCourseService.getAllUserCourses();
    return new ResponseEntity<>(userCourseDTOs, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<userCourseDTO> updateUserCourse(
      @PathVariable long id, @RequestBody userCourseDTO userCourseDTO) {
    userCourseDTO.setId(id);
    userCourseDTO updatedUserCourse = userCourseService.updateUserCourse(userCourseDTO);
    if (updatedUserCourse != null) {
      return new ResponseEntity<>(updatedUserCourse, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserCourse(@PathVariable long id) {
    userCourseService.deleteUserCourse(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
