package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST APIs for Announcement")
@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthAnnouncementController {
    private final AnnouncementService announcementService;

    @Operation(summary = "Create Announcement", description = "Create Announcement REST API is used to save Announcement in a database")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
    @PostMapping("/announcement")
    public ResponseEntity<AnnouncementDto> createAnnouncement(@Valid @RequestBody AnnouncementDto announcement) {

        AnnouncementDto createAnnouncement = announcementService.createAnnouncement(announcement);
        return new ResponseEntity<>(createAnnouncement, HttpStatus.CREATED);
    }
}
