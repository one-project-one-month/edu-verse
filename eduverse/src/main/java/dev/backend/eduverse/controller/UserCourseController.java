//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.UserCourseDto;
//import dev.backend.eduverse.service.UserCourseService;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/userCourses")
//public class UserCourseController {
//
//    @Autowired
//    private UserCourseService userCourseService;
//
//    @PostMapping
//    public ResponseEntity<UserCourseDto> createUserCourse(@RequestBody UserCourseDto userCourseDTO) {
//        UserCourseDto createdUserCourse = userCourseService.createUserCourse(userCourseDTO);
//        return new ResponseEntity<>(createdUserCourse, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserCourseDto> getUserCourseById(@PathVariable long id) {
//        UserCourseDto userCourseDTO = userCourseService.getUserCourseById(id);
//        if (userCourseDTO != null) {
//            return new ResponseEntity<>(userCourseDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserCourseDto>> getAllUserCourses() {
//        List<UserCourseDto> UserCourseDTOS = userCourseService.getAllUserCourses();
//        return new ResponseEntity<>(UserCourseDTOS, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserCourseDto> updateUserCourse(
//            @PathVariable long id, @RequestBody UserCourseDto userCourseDTO) {
//        userCourseDTO.setId(id);
//        UserCourseDto updatedUserCourse = userCourseService.updateUserCourse(userCourseDTO);
//        if (updatedUserCourse != null) {
//            return new ResponseEntity<>(updatedUserCourse, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUserCourse(@PathVariable long id) {
//        userCourseService.deleteUserCourse(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
