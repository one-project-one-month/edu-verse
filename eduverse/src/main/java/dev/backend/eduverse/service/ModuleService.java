package dev.backend.eduverse.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import dev.backend.eduverse.dto.ModuleDto;

public interface ModuleService {

	ModuleDto createModule(ModuleDto moduleDto);

	ModuleDto updateModule(ModuleDto moduleDto, Long id);

	void deleteModule(Long id);

	List<ModuleDto> getAllModules();

	ModuleDto getById(Long id);

	List<ModuleDto> getByCourseId(Long courseId);
}
