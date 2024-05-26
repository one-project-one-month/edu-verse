package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.UserCourseDTO;
import dev.backend.eduverse.service.UserCourseService;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.util.response_template.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthCourseController {
    private final UserServiceImpl userService;
    private final UserCourseService userCourseService;

    //    @PostMapping("/admin/course")
//    @GetMapping("/course/{user_id}/registered-courses")
    @GetMapping("/course/{user_id}/registered-courses")
    public ResponseEntity<?> getAllRegisterCourses(@PathVariable Long user_id) {

        List<Object[]> courseDTOList = userService.getAllRegisteredCourses(user_id);
        return ResponseEntity.ok().body(courseDTOList);
    }

    @PostMapping("/course/enroll")
    public ResponseEntity<ApiResponse<UserCourseDTO>> enroll(@Valid @RequestBody UserCourseDTO userCourseDTO) {

        UserCourseDTO savedUserCourse = userCourseService.createUserCourse(userCourseDTO);

        return ResponseEntity.ok()
                .body(
                        new ApiResponse<>(HttpStatus.OK, "Successfully Enrolled.", savedUserCourse)
                );
    }
}
