package dev.backend.eduverse.service;

import java.util.List;

import dev.backend.eduverse.dto.ModuleDto;

public interface ModuleService {

	ModuleDto createModule(ModuleDto moduleDto);

	ModuleDto updateModule(ModuleDto moduleDto, Long id);

	void deleteModule(Long id);

	List<ModuleDto> getAllModulesByPagination(int pageNo, int limit) throws IllegalAccessException;

	ModuleDto getById(Long id);

	List<ModuleDto> getByCourseId(Long courseId);

}
