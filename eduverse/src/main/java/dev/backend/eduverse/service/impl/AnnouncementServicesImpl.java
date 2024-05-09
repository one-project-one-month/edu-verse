package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.service.AnnouncementServices;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServicesImpl implements AnnouncementServices {
	public ResponseEntity<ApiResponse<String>> create(AnnouncementDTO announcementDTO, Long adminId, List<Long> courseId) {
		return  null;
	}

}
