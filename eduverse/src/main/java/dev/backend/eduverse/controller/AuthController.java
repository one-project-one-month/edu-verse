package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.dto.AuthDto;
import dev.backend.eduverse.dto.ResponseAuthDto;
import dev.backend.eduverse.dto.UserDTO;
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
    public ResponseEntity<ResponseAuthDto<UserDTO>> processUserLogin(@Valid @RequestBody AuthDto authDto) {

        ResponseAuthDto<UserDTO> responseAuthUserDto = authService.processUserLogin(authDto);

        return new ResponseEntity<>(responseAuthUserDto, HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ResponseAuthDto<AdminDto>> processAdminLogin(@Valid @RequestBody AuthDto authDto) {

        ResponseAuthDto<AdminDto> responseAuthAdminDto = authService.processAdminLogin(authDto);

        return new ResponseEntity<>(responseAuthAdminDto, HttpStatus.OK);
    }
}
