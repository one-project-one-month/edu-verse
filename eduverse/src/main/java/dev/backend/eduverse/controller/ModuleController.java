package dev.backend.eduverse.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.backend.eduverse.dto.ModuleDto;
import dev.backend.eduverse.service.ModuleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/module")
public class ModuleController {

	private ModuleService moduleService;

	public ModuleController(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@PostMapping("create")
	public ResponseEntity<ModuleDto> createModule(@Valid @RequestBody ModuleDto moduleDto) {
		return moduleService.createModule(moduleDto);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<ModuleDto> updateModule(@Valid @RequestBody ModuleDto moduleDto, @PathVariable Long id) {
		return moduleService.updateModule(moduleDto, id);
	}

	@DeleteMapping("delete/{id}")
	public void deleteModule(@PathVariable Long id) {
		moduleService.deleteModule(id);
	}

	@GetMapping("allModules")
	public ResponseEntity<List<ModuleDto>> getAllModules() {
		return moduleService.getAllModules();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModuleDto> getById(@PathVariable Long id) {
		return moduleService.getById(id);
	}

	@GetMapping("{courseId}/modules")
	public ResponseEntity<List<ModuleDto>> getByCourseId(@PathVariable Long courseId) {
		return moduleService.getByCourseId(courseId);
	}

}
