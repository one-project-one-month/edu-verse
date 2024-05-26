package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.dto.CategoryDto;
import dev.backend.eduverse.dto.CourseDTO;
import dev.backend.eduverse.model.Course;
import dev.backend.eduverse.service.AnnouncementService;
import dev.backend.eduverse.service.impl.CategoryServiceImpl;
import dev.backend.eduverse.service.impl.UserServiceImpl;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class SampleAuthController {
    private  final UserServiceImpl userService;

    @GetMapping("/admin/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user/{id}/courses/")
    public ResponseEntity<?> getAllRegisterCourses(@PathVariable Long id){

        List<Object[]> courseDTOList = userService.getAllRegisteredCourses(id);
        return ResponseEntity.ok().body(courseDTOList);
    }

}
