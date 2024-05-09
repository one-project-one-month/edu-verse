package dev.backend.eduverse.service;

import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnnouncementServices {
	public ResponseEntity<ApiResponse<String>> create(AnnouncementDTO announcementDTO, Long adminId, List<Long> courseId);
}
