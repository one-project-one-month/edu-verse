package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleAuthController {
    private  final UserServiceImpl userService;
    @GetMapping("/api/auth/admin/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/api/auth/user/{id}/courses/")
    public ResponseEntity<?> getAllRegisterCourses(@PathVariable Long id){

        List<Object[]> courseDTOList = userService.getAllRegisteredCourses(id);
        return ResponseEntity.ok().body(courseDTOList);
    }
}
