package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.AuthUserDto;
import dev.backend.eduverse.dto.ResponseAuthUserDto;
import dev.backend.eduverse.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseAuthUserDto> processUserLogin(@Valid @RequestBody AuthUserDto authUserDto) {

        ResponseAuthUserDto responseAuthUserDto = authService.processUserLogin(authUserDto);

        return new ResponseEntity<>(responseAuthUserDto, HttpStatus.OK);
    }
}
