/**
 * @Author : Kyaw Zaw Htet @Date : 5/10/2024 @Time : 11:06 PM @Project_Name : eduverse
 */
package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.AnnouncementDto;
import dev.backend.eduverse.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "CRUD REST APIs for Announcement",
    description =
        "CRUD REST APIs - Create Announcement, Update Announcement, Get Announcement By Id, Get All"
            + " Announcements, Delete Announcement")
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/announcements")
public class AnnouncementController {

  private AnnouncementService announcementService;

  @Operation(
      summary = "Get All Announcement REST API",
      description =
          "Get All Announcement REST API is used to get all the Announcements from the database")
  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
  @GetMapping
  public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements() {

    List<AnnouncementDto> announcements = announcementService.getAllAnnouncements();
    return new ResponseEntity<>(announcements, HttpStatus.OK);
  }

  @Operation(
      summary = "Get Announcement By Id REST API",
      description =
          "Get Announcement By Id REST API is used to get a single Announcement from the database")
  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
  @GetMapping("{id}")
  public ResponseEntity<AnnouncementDto> getAnnouncementById(@PathVariable("id") Long id) {

    AnnouncementDto announcement = announcementService.getAnnouncementById(id);
    return new ResponseEntity<>(announcement, HttpStatus.OK);
  }

  @Operation(
      summary = "Create Announcement REST API",
      description = "Create Announcement REST API is used to save Announcement in a database")
  @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
  @PostMapping("/create")
  public ResponseEntity<AnnouncementDto> createAnnouncement(
      @Valid @RequestBody AnnouncementDto announcement) {

    AnnouncementDto createAnnouncement = announcementService.createAnnouncement(announcement);
    return new ResponseEntity<>(createAnnouncement, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Update Announcement REST API",
      description =
          "Update Announcement REST API is used to update a particular Announcement in the"
              + " database")
  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
  @PutMapping("{id}")
  public ResponseEntity<AnnouncementDto> updateAnnouncement(
      @PathVariable("id") Long id, @RequestBody @Valid AnnouncementDto announcement) {

    announcement.setId(id);
    AnnouncementDto updatedAnnouncement = announcementService.updateAnnouncement(announcement);
    return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
  }

  @Operation(
      summary = "Delete Announcement REST API",
      description =
          "Delete Announcement REST API is used to delete a particular Announcement from the"
              + " database")
  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteAnnouncement(@PathVariable("id") Long id) {

    announcementService.deleteAnnouncement(id);
    return new ResponseEntity<>("Announcement successfully deleted!", HttpStatus.OK);
  }
}
