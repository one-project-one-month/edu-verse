package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.service.AdminService;
import dev.backend.eduverse.util.response_template.PageNumberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Admin")
@RestController
@RequestMapping("/api/auth/admin/admins")
@RequiredArgsConstructor
public class AuthAdminController {

    private final Logger logger = LoggerFactory.getLogger(AuthAdminController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private final AdminService adminService;

    // Get All
    @Operation(summary = "Get All Admins")
    @GetMapping("")
    public ResponseEntity<PageNumberResponse<List<AdminDto>>> getAllAdmins(
            @RequestParam(value = "search", required = false, defaultValue = "") String searchKeyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
    ) {

        List<AdminDto> admins = adminService.paginate(searchKeyword, pageNo, limit);

        return new ResponseEntity<>(
                new PageNumberResponse<List<AdminDto>>(pageNo, limit, admins),
                HttpStatus.OK
        );
    }

    // Get By Id
    @Operation(summary = "Get Admin By Id")
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getById(@PathVariable Long id) {
        AdminDto adminDto = adminService.findById(id);

        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

    // Create new
    @Operation(summary = "Create new Admin")
    @PostMapping("")
    public ResponseEntity<AdminDto> createAdmin(@Valid @RequestBody AdminDto adminDto) {
        AdminDto createdAdmin = adminService.createAdmin(adminDto);
        return new ResponseEntity<>(createdAdmin, HttpStatus.OK);
    }

    // Update
    @Operation(summary = "Update Admin By Id")
    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(
            @Valid @RequestBody AdminDto adminDto, @PathVariable Long id) {
        AdminDto updatedAdminDto = adminService.updateAdmin(adminDto, id);
        return new ResponseEntity<>(updatedAdminDto, HttpStatus.OK);
    }

    // Delete by Id
    @Operation(summary = "Delete Admin By Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>("Admin is successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
