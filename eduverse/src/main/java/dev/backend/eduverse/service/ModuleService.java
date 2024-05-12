package dev.backend.eduverse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import dev.backend.eduverse.dto.ModuleDto;

public interface ModuleService {

	ResponseEntity<ModuleDto> createModule(ModuleDto moduleDto);

	ResponseEntity<ModuleDto> updateModule(ModuleDto moduleDto, Long id);

	void deleteModule(Long id);

	ResponseEntity<List<ModuleDto>> getAllModules();

	ResponseEntity<ModuleDto> getById(Long id);

	ResponseEntity<List<ModuleDto>> getByCourseId(Long courseId);
}
