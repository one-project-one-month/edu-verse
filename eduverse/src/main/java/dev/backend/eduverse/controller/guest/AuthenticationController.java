package dev.backend.eduverse.controller.guest;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.dto.AuthDto;
import dev.backend.eduverse.dto.ResponseAuthDto;
import dev.backend.eduverse.dto.UserDTO;
import dev.backend.eduverse.service.AuthService;
import dev.backend.eduverse.service.UserService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/")
public class AuthenticationController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponseAuthDto<UserDTO>> processUserLogin(@Valid @RequestBody AuthDto authDto) {

        ResponseAuthDto<UserDTO> responseAuthUserDto = authService.processUserLogin(authDto);

        return new ResponseEntity<>(responseAuthUserDto, HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<ApiResponse<Object>> userRegister(@Valid @RequestBody UserDTO userDTO) {

        userService.createUser(userDTO);

        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.OK, "Successfully registered.", null),
                HttpStatus.OK
        );
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ResponseAuthDto<AdminDto>> processAdminLogin(@Valid @RequestBody AuthDto authDto) {

        ResponseAuthDto<AdminDto> responseAuthAdminDto = authService.processAdminLogin(authDto);

        return new ResponseEntity<>(responseAuthAdminDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !authService.logout(token)) {
            return new ResponseEntity<>(
                    new ApiResponse<>(HttpStatus.UNAUTHORIZED, "Logout FAILED.", null),
                    HttpStatus.UNAUTHORIZED
            );
        }

        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.OK, "Logout success.", null),
                HttpStatus.OK
        );
    }
}