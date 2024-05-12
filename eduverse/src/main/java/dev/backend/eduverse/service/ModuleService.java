package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.ModuleDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ModuleService {

  ResponseEntity<ModuleDto> createModule(ModuleDto moduleDto);

  ResponseEntity<ModuleDto> updateModule(ModuleDto moduleDto, Long id);

  void deleteModule(Long id);

  ResponseEntity<List<ModuleDto>> getAllModules();

  ResponseEntity<ModuleDto> getById(Long id);

  ResponseEntity<List<ModuleDto>> getByCourseId(Long courseId);
}
