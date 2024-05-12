package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.AdminDto;
import dev.backend.eduverse.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //Get All
    @Tag(name = "Get All Admins")
    @Operation(tags = "Admin Operation")
    @GetMapping("")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.findAll();

        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    //Get By Id
    @Tag(name = "Get Admin By Id")
    @Operation(tags = "Admin Operation")
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getById(@PathVariable Long id) {
        AdminDto adminDto = adminService.findById(id);

        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }

    //Create new
    @Tag(name = "Create Admin")
    @Operation(tags = "Admin Operation")
    @PostMapping("")
    public ResponseEntity<AdminDto> createAdmin(@Valid @RequestBody AdminDto adminDto) {
        AdminDto createdAdmin = adminService.createAdmin(adminDto);
        return new ResponseEntity<>(createdAdmin, HttpStatus.OK);
    }

    //Update
    @Tag(name = "Update Admin")
    @Operation(tags = "Admin Operation")
    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@Valid @RequestBody AdminDto adminDto, @PathVariable Long id) {
        AdminDto updatedAdminDto = adminService.updateAdmin(adminDto, id);
        return new ResponseEntity<>(updatedAdminDto, HttpStatus.OK);
    }

    //Delete by Id
    @Tag(name = "Delete Admin")
    @Operation(tags = "Admin Operation")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteById(id);
        return new ResponseEntity<>("Admin is successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
