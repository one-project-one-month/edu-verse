package dev.backend.eduverse.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleAuthController {
    @GetMapping("/api/auth/admin/hello")
    public String hello() {
        return "Hello World";
    }
}
